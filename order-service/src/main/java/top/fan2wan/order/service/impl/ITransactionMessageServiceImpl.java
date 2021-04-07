package top.fan2wan.order.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;
import top.fan2wan.database.rocketmq.support.ITransactionMessageService;
import top.fan2wan.database.rocketmq.support.TransactionArgExt;

/**
 * @Author: fanT
 * @Date: 2021/4/2 14:25
 * @Description: impl for ITransactionMessageService
 */
@Slf4j
@Service
public class ITransactionMessageServiceImpl implements ITransactionMessageService {
    /**
     * 保存事务消息
     *
     * @param message message
     * @param ext     args
     * @return boolean
     */
    @Override
    public boolean saveTransactionMsg(Message message, TransactionArgExt ext) {
        log.info("saveTransactionMsg -- save msg success...");

        return true;
    }

    /**
     * check localTransaction with rocketMqTransactionId
     *
     * @param transactionId transactionId
     * @return localState  true -> commit
     */
    @Override
    public boolean checkStateWithTransactionId(String transactionId) {
        log.info("checkStateWithTransactionId --transactionId was :{}", transactionId);
        return true;
    }
}
