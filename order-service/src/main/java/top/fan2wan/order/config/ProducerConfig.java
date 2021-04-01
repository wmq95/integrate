package top.fan2wan.order.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.fan2wan.database.rocketmq.producer.config.AbstractProducerConfig;
import top.fan2wan.order.service.IUserOrderService;

/**
 * @Author: fanT
 * @Date: 2021/3/29 13:46
 * @Description: config for producer
 */
@Configuration
@Slf4j
public class ProducerConfig extends AbstractProducerConfig {

    @Autowired
    private IUserOrderService userOrderService;

    /**
     * 事务消息生产者
     *
     * @return TransactionMQProducer
     */
    @Bean
    public TransactionMQProducer transactionMQProducer() {
        TransactionMQProducer producer = new TransactionMQProducer(groupName);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setVipChannelEnabled(false);
        producer.setMaxMessageSize(maxMessageSize);
        producer.setSendMsgTimeout(sendMsgTimeOut);
        producer.setRetryTimesWhenSendAsyncFailed(retryTimesWhenSendFailed);

        producer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
//                final String transactionId = msg.getTransactionId();
//                log.info("txMsg -- transactionId was :{}", transactionId);
                log.info("txMsg -- msg was :{},\n arg was :{}", msg, arg);

                LocalTransactionState state = LocalTransactionState.ROLLBACK_MESSAGE;
                try {
                    userOrderService.saveWithRollback();
                    state = LocalTransactionState.COMMIT_MESSAGE;
                } finally {
                    log.info("executeLocalTransaction -- return rollback");
                    return state;
                }
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                log.info("checkTxMsgTransaction .....");
                try {
                    Thread.sleep(1000 * 5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });

        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return producer;
    }
}
