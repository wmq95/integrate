package top.fan2wan.database.rocketmq.simple;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.context.annotation.Bean;
import top.fan2wan.database.rocketmq.support.AbstractProducerConfig;

/**
 * @Author: fanT
 * @Date: 2021/4/1 13:20
 * @Description: config for DefaultMQProducer
 */
public abstract class DefaultMqProducerConfig extends AbstractProducerConfig {

    /**
     * 生成默认的mq producer
     *
     * @return DefaultMQProducer
     * @throws MQClientException MQClientException
     */
    @Bean
    public DefaultMQProducer defaultProducer() throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setVipChannelEnabled(false);
        producer.setMaxMessageSize(maxMessageSize);
        producer.setSendMsgTimeout(sendMsgTimeOut);
        producer.setRetryTimesWhenSendAsyncFailed(retryTimesWhenSendFailed);
        producer.start();
        LOGGER.debug("defaultProducer -- create success");
        return producer;
    }
}
