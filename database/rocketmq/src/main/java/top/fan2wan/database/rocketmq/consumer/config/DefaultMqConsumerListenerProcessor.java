package top.fan2wan.database.rocketmq.consumer.config;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * @Author: fanT
 * @Date: 2021/3/30 9:18
 * @Description:
 */
class DefaultMqConsumerListenerProcessor implements MessageListenerConcurrently {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMqConsumerListenerProcessor.class);
    private static final String CHARSET_NAME = "utf-8";

    /**
     * It is not recommend to throw exception,rather than returning ConsumeConcurrentlyStatus.RECONSUME_LATER if
     * consumption failure
     *
     * @param msgs    msgs.size() >= 1<br> DefaultMQPushConsumer.consumeMessageBatchMaxSize=1,you can modify here
     * @param context
     * @return The consume status
     */
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        // 消息为空
        if (CollectionUtils.isEmpty(msgs)) {
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt msg = msgs.get(0);
        if (Objects.isNull(msg)) {
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        LOGGER.info("consumer receive msg success, msg was :{}", msg.toString());

        try {
            String topic = msg.getTopic();
            String tags = msg.getTags();
            String body = new String(msg.getBody(), CHARSET_NAME);

            LOGGER.info("topic was :{}, tags was :{}, body was :{}", topic, tags, body);
        } catch (Exception e) {
            LOGGER.error("获取MQ消息内容异常{}", e.getMessage());
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
