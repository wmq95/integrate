package top.fan2wan.test.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import top.fan2wan.test.config.MqConfig;

/**
 * @Author: fanT
 * @Date: 2020/12/7 9:59
 * @Description: receiver by topic
 */
@Component
public class TopicReceiver {

    @Bean
    public Queue topic_queue1() {
        return new Queue("queue_topic_1");
    }

    @Bean
    public Queue topic_queue2() {
        return new Queue("queue_topic_2");
    }


    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(MqConfig.EXCHANGE_TOPIC);
    }

    @Bean
    public Binding topic_binding1() {
        return BindingBuilder
                .bind(topic_queue1()).to(topicExchange()).with("topic.#");
    }

    @Bean
    public Binding topic_binding2() {
        return BindingBuilder
                .bind(topic_queue2()).to(topicExchange()).with("topic.*");
    }


    @RabbitListener(queues = "queue_topic_1")
    public void receiveMsg1(String msg) {
        System.out.println("消费者1接收到：" + msg);
    }

    @RabbitListener(queues = "queue_topic_2")
    public void receiveMsg2(String msg) {
        System.out.println("消费者2接收到：" + msg);
    }
}
