package top.fan2wan.order.config;

import org.apache.rocketmq.client.producer.TransactionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import top.fan2wan.database.rocketmq.support.ITransactionMessageService;
import top.fan2wan.database.rocketmq.support.ITransactionMsgHandler;
import top.fan2wan.database.rocketmq.tx.AbstractTransactionMqConfig;
import top.fan2wan.database.rocketmq.tx.TransactionListenerImpl;

import java.util.List;

/**
 * @Author: fanT
 * @Date: 2021/3/29 13:46
 * @Description: config for producer
 */
//@Configuration
//@Slf4j
//public class ProducerConfig extends AbstractProducerConfig {
//
//    @Autowired
//    private IUserOrderService userOrderService;
//
//    /**
//     * 事务消息生产者
//     *
//     * @return TransactionMQProducer
//     */
//    @Bean
//    public TransactionMQProducer transactionMQProducer() {
//        TransactionMQProducer producer = new TransactionMQProducer(groupName);
//        producer.setNamesrvAddr(namesrvAddr);
//        producer.setVipChannelEnabled(false);
//        producer.setMaxMessageSize(maxMessageSize);
//        producer.setSendMsgTimeout(sendMsgTimeOut);
//        producer.setRetryTimesWhenSendAsyncFailed(retryTimesWhenSendFailed);
//
//        producer.setTransactionListener(new TransactionListener() {
//            @Override
//            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
////                final String transactionId = msg.getTransactionId();
////                log.info("txMsg -- transactionId was :{}", transactionId);
//                log.info("txMsg -- msg was :{},\n arg was :{}", msg, arg);
//
//                LocalTransactionState state = LocalTransactionState.ROLLBACK_MESSAGE;
//                try {
//                    userOrderService.saveWithRollback();
//                    state = LocalTransactionState.COMMIT_MESSAGE;
//                } finally {
//                    log.info("executeLocalTransaction -- return rollback");
//                    return state;
//                }
//            }
//
//            @Override
//            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
//                log.info("checkTxMsgTransaction .....");
//                try {
//                    Thread.sleep(1000 * 5);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    return LocalTransactionState.ROLLBACK_MESSAGE;
//                }
//                return LocalTransactionState.COMMIT_MESSAGE;
//            }
//        });
//
//        try {
//            producer.start();
//        } catch (MQClientException e) {
//            e.printStackTrace();
//        }
//        return producer;
//    }
//}

@Configuration
public class ProducerConfig extends AbstractTransactionMqConfig {

    @Autowired
    private List<ITransactionMsgHandler> msgHandlers;
    @Autowired
    private ITransactionMessageService transactionMessageService;

    @Autowired
    /**
     * listener
     *
     * @return TransactionListener
     */
    @Override
    protected TransactionListener transactionListener() {
        /**
         * 在实现类里面 需要其他的service
         * 正好可以使用懒加载的原理
         * 自己手动去加载
         */
        return new TransactionListenerImpl(msgHandlers, transactionMessageService);
    }
}
