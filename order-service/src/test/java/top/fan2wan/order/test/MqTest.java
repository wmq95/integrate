package top.fan2wan.order.test;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.fan2wan.order.OrderApplication;

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

    @Test
    public void testTxMsg() {
        Message sendMsg = new Message("MyTopic", "MyTag", "hello rocketMq".getBytes());
        try {
            producer.sendMessageInTransaction(sendMsg, null);
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000 * 60 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
