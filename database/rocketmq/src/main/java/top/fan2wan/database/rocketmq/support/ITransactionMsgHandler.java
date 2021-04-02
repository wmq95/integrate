package top.fan2wan.database.rocketmq.support;

/**
 * @Author: fanT
 * @Date: 2021/4/1 14:19
 * @Description: interface for handler transactionMsg
 */
public interface ITransactionMsgHandler {

    /**
     * 是否支持当前的事务消息
     *
     * @param arg 这个arg 对应发送事务消息的入参
     * @return true 支持
     */
    boolean support(TransactionArgExt arg);

    /**
     * 操作本地事务
     *
     * @param arg 参数
     */
    void doLocationTransaction(TransactionArgExt arg);
}
