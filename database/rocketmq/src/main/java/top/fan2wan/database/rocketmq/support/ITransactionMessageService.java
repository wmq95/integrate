package top.fan2wan.database.rocketmq.support;

import org.apache.rocketmq.common.message.Message;

/**
 * @Author: fanT
 * @Date: 2021/4/2 9:18
 * @Description: interface for transactionMsg
 */
public interface ITransactionMessageService {

    /**
     * 保存事务消息
     *
     * @param message message
     * @param ext     args
     * @return boolean
     */
    boolean saveTransactionMsg(Message message, TransactionArgExt ext);

    /**
     * check localTransaction with rocketMqTransactionId
     *
     * @param transactionId transactionId
     * @return localState  true -> commit
     */
    boolean checkStateWithTransactionId(String transactionId);
}
