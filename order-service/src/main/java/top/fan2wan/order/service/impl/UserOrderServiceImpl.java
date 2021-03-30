package top.fan2wan.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fan2wan.common.util.IdGenerator;
import top.fan2wan.order.entity.UserOrder;
import top.fan2wan.order.manager.UserManager;
import top.fan2wan.order.mapper.UserOrderMapper;
import top.fan2wan.order.service.IUserOrderService;

import java.time.LocalDateTime;

//import io.seata.spring.annotation.GlobalTransactional;

/**
 * <p>
 * order 服务实现类
 * </p>
 *
 * @author fanT
 * @since 2021-03-25
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements IUserOrderService {

    private final UserManager userManager;
    private final MQProducer producer;

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @GlobalTransactional
    public Boolean saveTest() {
        UserOrder userOrder = new UserOrder();
        userOrder.setId(IdGenerator.getId());
        userOrder.setGmtCreate(LocalDateTime.now());
        userOrder.setGmtModified(userOrder.getGmtCreate());
        userOrder.setUserId(userOrder.getId());
        log.info("saveTest -- save entity was :{}", userOrder);

        userManager.saveTest();
        log.info("call for user success....");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("start to save userOrder");
        int a = 1 / 0;
        return save(userOrder);
    }

    @Override
    public Boolean sendMqMsg() {

        Message sendMsg = new Message("MyTopic", "MyTag", "hello rocketMq".getBytes());
        // 默认3秒超时
        SendResult sendResult = null;
        try {
            sendResult = producer.send(sendMsg);
            log.info("消息发送响应：" + sendResult.toString());
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}
