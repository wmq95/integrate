package top.fan2wan.database.rocketmq.util;

import com.sun.istack.internal.NotNull;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: fanT
 * @Date: 2021/4/1 13:29
 * @Description: util for producer
 */
public class ProducerUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerUtil.class);
    private final MQProducer producer;

    public ProducerUtil(MQProducer producer) {
        this.producer = producer;
    }

    /**
     * 发送简单的message
     *
     * @param topic   主体
     * @param content 内同
     * @param tag     标签
     * @return SendResult
     * @throws Exception MQClientException, RemotingException, MQBrokerException, InterruptedException
     */
    public SendResult sendMsg(@NotNull String topic, @NotNull String tag,
                              @NotNull String content) throws Exception {

        return producer.send(new Message(topic, tag, content.getBytes()));
    }

    /**
     * 发送半事务消息
     *
     * @param topic   主题
     * @param content 内容
     * @param tag     标签
     * @param extData 扩展字段 会透传到事务监听器里
     * @return TransactionSendResult
     * @throws Exception MQClientException,IllegalArgumentException
     */
    public TransactionSendResult sendTxMsg(@NotNull String topic, @NotNull String tag,
                                           @NotNull String content, Object extData) throws Exception {
      /*  if (Objects.isNull(producer)
                || !(producer instanceof TransactionMQProducer)) {
            LOGGER.error("producer is illegal, can not send transaction message");
            throw new IllegalArgumentException("producer did not support transactional message");
        }*/
        // 无需做验证 sendMessageInTransaction 自己实现了
        return producer.sendMessageInTransaction(new Message(topic, tag, content.getBytes()), extData);
    }
}
