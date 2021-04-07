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
import top.fan2wan.database.rocketmq.support.TransactionArgExt;
import top.fan2wan.order.bo.UserIntegralBO;
import top.fan2wan.order.constants.StringConstant;

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

    public Boolean placeOrderWithMq(Long userId) {
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
        return LocalTransactionState.COMMIT_MESSAGE.equals(transactionSendResult.getLocalTransactionState());
    }
}
