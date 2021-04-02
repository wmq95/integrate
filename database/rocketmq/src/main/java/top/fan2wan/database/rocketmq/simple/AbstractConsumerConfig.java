package top.fan2wan.database.rocketmq.simple;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @Author: fanT
 * @Date: 2021/3/29 13:37
 * @Description: config for consumer
 */
@ConfigurationProperties(prefix = "rocketmq.consumer")
public abstract class AbstractConsumerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractConsumerConfig.class);

    private String groupName;
    private String namesrvAddr;
    private String topics;
    // 消费者线程数据量
    private Integer consumeThreadMin;
    private Integer consumeThreadMax;
    private Integer consumeMessageBatchMaxSize;

    @Autowired
    private MessageListenerConcurrently consumeMsgListenerProcessor;

    /**
     * mq 消费者配置
     *
     * @return DefaultMQPushConsumer
     * @throws MQClientException
     */
    @Bean
    @ConditionalOnMissingBean(MQPushConsumer.class)
    public DefaultMQPushConsumer defaultConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        // 设置监听
        consumer.registerMessageListener(consumeMsgListenerProcessor);

        /**
         * 设置consumer第一次启动是从队列头部开始还是队列尾部开始
         * 如果不是第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        /**
         * 设置消费模型，集群还是广播，默认为集群
         */
//        consumer.setMessageModel(MessageModel.CLUSTERING);
        try {
            // 设置该消费者订阅的主题和tag，如果订阅该主题下的所有tag，则使用*,
            String[] topicArr = topics.split(";");
            for (String tag : topicArr) {
                String[] tagArr = tag.split("~");
                consumer.subscribe(tagArr[0], tagArr[1]);
            }
            if (consumeMsgListenerProcessor == null) {
                // 初始化listener
                consumeMsgListenerProcessor = messageListenerConcurrently();
            }
            consumer.start();
            LOGGER.info("defaultConsumer -- create success");
        } catch (MQClientException e) {
            LOGGER.error("defaultConsumer -- create failed, error was :{}", e.getErrorMessage());
            e.printStackTrace();
        }
        return consumer;
    }

    @Bean
    public MessageListenerConcurrently messageListenerConcurrently() {
        return new DefaultMqConsumerListenerProcessor();
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public void setConsumeThreadMin(Integer consumeThreadMin) {
        this.consumeThreadMin = consumeThreadMin;
    }

    public void setConsumeThreadMax(Integer consumeThreadMax) {
        this.consumeThreadMax = consumeThreadMax;
    }

    public void setConsumeMessageBatchMaxSize(Integer consumeMessageBatchMaxSize) {
        this.consumeMessageBatchMaxSize = consumeMessageBatchMaxSize;
    }

    public void setConsumeMsgListenerProcessor(MessageListenerConcurrently consumeMsgListenerProcessor) {
        this.consumeMsgListenerProcessor = consumeMsgListenerProcessor;
    }
}


