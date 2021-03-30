package top.fan2wan.order.config;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.fan2wan.database.rocketmq.producer.config.AbstractProducerConfig;

/**
 * @Author: fanT
 * @Date: 2021/3/29 13:46
 * @Description: config for producer
 */
@Configuration
public class ProducerConfig extends AbstractProducerConfig {

   /* @Bean
    public DefaultMQProducer defaultMQProducer() {
        return new DefaultMQProducer();
    }*/
}
