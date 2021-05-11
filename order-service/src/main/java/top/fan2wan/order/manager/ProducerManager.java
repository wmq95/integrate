package top.fan2wan.order.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;
import top.fan2wan.database.redis.util.RedisUtil;
import top.fan2wan.database.rocketmq.support.TransactionArgExt;
import top.fan2wan.order.bo.UserIntegralBO;
import top.fan2wan.order.constant.StringConstant;

import java.util.Optional;

/**
 * @Author: fanT
 * @Date: 2021/4/7 14:29
 * @Description: manger for producer
 */
@Service
@AllArgsConstructor
@Slf4j
public class ProducerManager {

    private final MQProducer producer;
    private final ObjectMapper mapper;
    private final RedisUtil redisUtil;

    public Boolean placeOrderWithMq(Long userId) {

        /**
         * 检查库存
         * 使用redis 做库存检查 问题点 减库存时机  是放在service 开始就去减库存 还是放在mq 半消息中见库存
         * 放在rocketmq 半消息中减库存 比较合适
         * 如果 减完库存 服务崩溃了 那么导致库存不能补偿
         * 放入半事务消息中减库存 就算发送失败 在消息回滚的时候去对redis 进行补偿
         * 那么需要实现 回滚的时候触发redis 补偿
         *
         * 在rocketmq 执行事务消息中对redis 进行补偿 如果服务崩溃 在rocketmq 会查接口进行补偿 但是需要确保幂等不能进行多次补偿
         * 那么在此service 中可以先进行redis 数量的一个预先检验 先获取数量 如果小于 1  直接返回
         */
        Optional<Integer> number = redisUtil.getAsInteger(StringConstant.USER_ORDER_NUMBER_KEY);

        if (!number.isPresent() || number.get() < 1) {
            return false;
        }

        /*argExt包含下一步骤需要的业务参数 需要跟消息分开*/
        TransactionArgExt argExt = new TransactionArgExt();
        argExt.setType(StringConstant.USER_INTEGRAL);
        argExt.setData(userId.toString());

        /*这个用户发送到user  应该存放在消息载体里面*/
        UserIntegralBO bo = new UserIntegralBO();
        bo.setUserId(userId);
        bo.setNumber(1);


        Message sendMsg = null;
        TransactionSendResult transactionSendResult = null;
        try {
            sendMsg = new Message("userTopic", "MyTag", mapper.writeValueAsString(bo).getBytes());
            transactionSendResult = producer.sendMessageInTransaction(sendMsg, argExt);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        log.info("placeOrderWithMq -- transactionSendResult was :{}", transactionSendResult);
        final boolean flag = LocalTransactionState.COMMIT_MESSAGE.equals(transactionSendResult.getLocalTransactionState());

        if (!flag) {
            // 事务失败 需要把失败得库存放入池中
            redisUtil.increase(StringConstant.USER_ORDER_NUMBER_KEY, 1);
        }
        return flag;
    }
}
