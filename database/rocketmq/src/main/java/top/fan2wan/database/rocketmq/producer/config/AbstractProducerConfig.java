package top.fan2wan.database.rocketmq.producer.config;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;


/**
 * @Author: fanT
 * @Date: 2021/3/29 13:23
 * @Description: config for producer
 */
@ConfigurationProperties(prefix = "rocketmq.producer")
public abstract class AbstractProducerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractProducerConfig.class);

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public void setMaxMessageSize(Integer maxMessageSize) {
        this.maxMessageSize = maxMessageSize;
    }

    public void setSendMsgTimeOut(Integer sendMsgTimeOut) {
        this.sendMsgTimeOut = sendMsgTimeOut;
    }

    public void setRetryTimesWhenSendFailed(Integer retryTimesWhenSendFailed) {
        this.retryTimesWhenSendFailed = retryTimesWhenSendFailed;
    }

    protected String groupName;
    protected String namesrvAddr;
    protected Integer maxMessageSize;
    protected Integer sendMsgTimeOut;
    protected Integer retryTimesWhenSendFailed;


    @Bean
    @ConditionalOnMissingBean(MQProducer.class)
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
