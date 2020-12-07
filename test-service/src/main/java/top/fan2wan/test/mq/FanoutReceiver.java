package top.fan2wan.test.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import top.fan2wan.test.config.MqConfig;


/**
 * @Author: fanT
 * @Date: 2020/12/7 9:39
 * @Description: receiver by fanout
 */
@Component
public class FanoutReceiver {

    @Bean
    public Queue queue1() {
        return new Queue("queue_fanout_1");
    }

    @Bean
    public Queue queue2() {
        return new Queue("queue_fanout_2");
    }


    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(MqConfig.EXCHANGE_FANOUT);
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queue1()).to(fanoutExchange());
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(fanoutExchange());
    }


    @RabbitListener(queues = "queue_fanout_1")
    public void receiveMsg1(String msg) {
        System.out.println("队列1接收到消息：" + msg);
    }

    @RabbitListener(queues = "queue_fanout_2")
    public void receiveMsg2(String msg) {
        System.out.println("队列2接收到消息：" + msg);
    }
}
