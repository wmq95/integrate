package top.fan2wan.database.rocketmqstart.util;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

/**
 * @Author: fanT
 * @Date: 2021/3/31 9:11
 * @Description: util for rocketmq
 */
public class RocketMqUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMqUtil.class);
    private static final RocketMQLocalTransactionListener DEFAULT_TRANSACTION_LISTENER = getListener();

    private static RocketMQLocalTransactionListener getListener() {
        LOGGER.info("init default transactional listener");
        return new RocketMQLocalTransactionListener() {
            @Override
            public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                LOGGER.info("msg was :{}", msg.getPayload());
                LOGGER.info("arg was :{}", arg);
                return RocketMQLocalTransactionState.COMMIT;
            }

            @Override
            public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
                return RocketMQLocalTransactionState.COMMIT;
            }
        };
    }

    @Autowired
    private RocketMQTemplate mqTemplate;

    /**
     * 同步发送消息 会抛出异常 自己判断是否要捕获
     *
     * @param destination topic
     * @param message     msg
     * @return sendResult
     */
    public SendResult syncSendMsg(String destination, Message message) {

        return mqTemplate.syncSend(destination, message);
    }


    public TransactionSendResult sendMsgTransactional(String group, String destination, Message msg) {

        return sendMsgTransactional(group, destination, msg, DEFAULT_TRANSACTION_LISTENER);
    }

    private TransactionSendResult sendMsgTransactional(String group, String destination,
                                                       Message msg, RocketMQLocalTransactionListener listener) {

        return mqTemplate.sendMessageInTransaction(group, destination, msg, listener);
    }
}
