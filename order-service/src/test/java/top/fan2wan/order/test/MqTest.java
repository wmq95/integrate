package top.fan2wan.order.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.fan2wan.common.util.IdGenerator;
import top.fan2wan.database.rocketmq.support.TransactionArgExt;
import top.fan2wan.order.OrderApplication;
import top.fan2wan.order.entity.UserOrder;

import java.time.LocalDateTime;

/**
 * @Author: fanT
 * @Date: 2021/4/1 9:34
 * @Description: test for mq
 */
@SpringBootTest(classes = OrderApplication.class)
@RunWith(SpringRunner.class)
public class MqTest {

    @Autowired
    private MQProducer producer;
    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testTxMsg() {
        Message sendMsg = new Message("MyTopic", "MyTag", "hello rocketMq".getBytes());
        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(IdGenerator.getId());
        userOrder.setId(userOrder.getUserId());
        userOrder.setGmtModified(LocalDateTime.now());
        try {
            TransactionArgExt ext = new TransactionArgExt();
            ext.setData(mapper.writeValueAsString(userOrder));
            producer.sendMessageInTransaction(sendMsg, ext);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("shutdown");
        /*try {
            Thread.sleep(1000 * 60 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
