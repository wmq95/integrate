package top.fan2wan.database.rocketmq.tx;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.fan2wan.database.rocketmq.support.ITransactionMessageService;
import top.fan2wan.database.rocketmq.support.ITransactionMsgHandler;
import top.fan2wan.database.rocketmq.support.TransactionArgExt;

import java.util.List;
import java.util.Objects;

/**
 * @Author: fanT
 * @Date: 2021/4/1 14:07
 * @Description: listener for transaction
 */
public class TransactionListenerImpl implements TransactionListener {
    private static Logger logger = LoggerFactory.getLogger(TransactionListenerImpl.class);

    /**
     * 目标service 的引用  为了便于扩展 是个list
     */
    final private List<ITransactionMsgHandler> msgHandlers;
    final private ITransactionMessageService transactionMessageService;

    public TransactionListenerImpl(List<ITransactionMsgHandler> msgHandlers, ITransactionMessageService transactionMessageService) {
        this.msgHandlers = msgHandlers;
        this.transactionMessageService = transactionMessageService;
    }

    /**
     * 确保消息和本地事务属于同一个事务
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {

        LocalTransactionState state = LocalTransactionState.ROLLBACK_MESSAGE;
        if (Objects.isNull(arg)) {
            //  入参不可以为null  很简单 因为这儿需要执行本地事务 参数应该传递进来
            //  直接rollback
            return state;
        }

        TransactionArgExt ext = null;
        try {
            ext = (TransactionArgExt) arg;
        } catch (ClassCastException e) {
            logger.error("illegal arg, can not cast to TransactionArgExt");
            return state;
        }

        try {
            //  1记录message 状态 transactionId 等 用于之后rocketmq 发起查询 把arg 也记录下去
            transactionMessageService.saveTransactionMsg(msg, ext);
            //  这一步 可以放在发送半事务消息的时候入库记录 无需放在这儿？但是有个问题 因为transactionId是发送成功之后 mq返回给我们的
            //  如果还没成功记录事务消息 就先触发了executeLocalTransaction 的回调 这个时候就会出现问题

            //  2执行本地业务逻辑
            TransactionArgExt finalExt = ext;
            msgHandlers.stream().filter(e -> e.support(finalExt))
                    .forEach(e -> e.doLocationTransaction(finalExt));

            //  无异常 返回commit
            state = LocalTransactionState.COMMIT_MESSAGE;
        } catch (Exception e) {
            // 3异常处理本地操作 但是注意 catch 之后要抛出异常 不然不会回滚
            transactionMessageService.rollbackForMsg(msg, e);
            throw new RuntimeException(e);
        } finally {
            // 确保返回本地事务状态
            if (LocalTransactionState.ROLLBACK_MESSAGE.equals(state)) {
                logger.error("msg was :{}\narg was :{}\n", msg, arg);
            }
            return state;
        }
    }


    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {

        final String transactionId = msg.getTransactionId();
        boolean stateFlag = transactionMessageService.checkStateWithTransactionId(transactionId);

        return stateFlag ? LocalTransactionState.COMMIT_MESSAGE : LocalTransactionState.ROLLBACK_MESSAGE;
    }
}
