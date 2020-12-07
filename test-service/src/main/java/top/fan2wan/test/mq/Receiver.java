package top.fan2wan.test.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import top.fan2wan.test.config.MqConfig;

/**
 * @Author: fanT
 * @Date: 2020/12/4 15:39
 * @Description: receiver for mq
 */
@Component
public class Receiver {
    @RabbitListener(queues = MqConfig.QUEUE_NAME)
    public void receiveMessage(String msg, Channel channel, Message message) {
        // 只包含发送的消息
        System.out.println("hello :" + msg);
       /* // channel 通道信息
        System.out.println(channel);
        // message 附加的参数信息
        System.out.println(message);*/
    }

    @RabbitListener(queues = MqConfig.QUEUE_NAME)
    public void receiveMessage2(String msg, Channel channel, Message message) {
        // 只包含发送的消息
        System.out.println("2 hello :" + msg);
/*        // channel 通道信息
        System.out.println(channel);
        // message 附加的参数信息
        System.out.println(message);*/
    }
}
