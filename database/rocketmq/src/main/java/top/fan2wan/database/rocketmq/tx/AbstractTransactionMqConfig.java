package top.fan2wan.database.rocketmq.tx;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.context.annotation.Bean;
import top.fan2wan.database.rocketmq.support.AbstractProducerConfig;

/**
 * @Author: fanT
 * @Date: 2021/4/1 13:25
 * @Description: config for transactionalMsg
 */
public abstract class AbstractTransactionMqConfig extends AbstractProducerConfig {

    @Bean
    public TransactionMQProducer transactionMQProducer() throws MQClientException {
        TransactionMQProducer producer = new TransactionMQProducer(groupName);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setVipChannelEnabled(false);
        producer.setMaxMessageSize(maxMessageSize);
        producer.setSendMsgTimeout(sendMsgTimeOut);
        producer.setRetryTimesWhenSendAsyncFailed(retryTimesWhenSendFailed);
        producer.setTransactionListener(transactionListener());
        producer.start();
        return producer;
    }

    /**
     * listener
     *
     * @return TransactionListener
     */
    protected abstract TransactionListener transactionListener();
}
