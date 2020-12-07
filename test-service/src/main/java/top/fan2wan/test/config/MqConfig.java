package top.fan2wan.test.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: fanT
 * @Date: 2020/12/4 15:34
 * @Description: config for rabbitMq
 */
@Component
public class MqConfig {
    public static final String QUEUE_NAME = "queue_work";

    public static final String EXCHANGE_FANOUT = "exchange_fanout";

    public static final String EXCHANGE_TOPIC = "exchange_topic";

    @Bean
    public Queue queueWork() {
        return new Queue(QUEUE_NAME);
    }
}
