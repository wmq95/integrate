package top.fan2wan.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fan2wan.common.util.IdGenerator;
import top.fan2wan.database.rocketmq.support.ITransactionMsgHandler;
import top.fan2wan.database.rocketmq.support.TransactionArgExt;
import top.fan2wan.order.bo.UserIntegralBO;
import top.fan2wan.order.constants.StringConstant;
import top.fan2wan.order.entity.UserOrder;
import top.fan2wan.order.manager.ProducerManager;
import top.fan2wan.order.manager.UserManager;
import top.fan2wan.order.mapper.UserOrderMapper;
import top.fan2wan.order.service.IUserOrderService;

import java.time.LocalDateTime;

/**
 * <p>
 * order 服务实现类
 * </p>
 *
 * @author fanT
 * @since 2021-03-25
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder>
        implements IUserOrderService, ITransactionMsgHandler {

    private final UserManager userManager;
//    private final ProducerManager producerManager;
//

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @GlobalTransactional
    public Boolean saveTest() {
        UserOrder userOrder = new UserOrder();
        userOrder.setId(IdGenerator.getId());
        userOrder.setGmtCreate(LocalDateTime.now());
        userOrder.setGmtModified(userOrder.getGmtCreate());
        userOrder.setUserId(userOrder.getId());
        log.info("saveTest -- save entity was :{}", userOrder);

        userManager.saveTest();
        log.info("call for user success....");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("start to save userOrder");
        int a = 1 / 0;
        return save(userOrder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveWithRollback() {
        UserOrder userOrder = new UserOrder();
        userOrder.setId(IdGenerator.getId());
        userOrder.setGmtCreate(LocalDateTime.now());
        userOrder.setGmtModified(userOrder.getGmtCreate());
        userOrder.setUserId(userOrder.getId());

        save(userOrder);

        log.info("saveWithRollback -- save entity success...throws exception");

        int a = 1 / 0;
        return a == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @GlobalTransactional
    public Boolean placeOrder(Long userId) {

        UserOrder userOrder = new UserOrder();
        userOrder.setId(IdGenerator.getId());
        userOrder.setUserId(userId);
        userOrder.setGmtCreate(LocalDateTime.now());
        userOrder.setGmtModified(userOrder.getGmtCreate());
        log.info("placeOrder -- save entity was :{}", userOrder);

        if (save(userOrder)) {
            // 调用user 新增积分
            userManager.addIntegral(userId);
        }
        return true;
    }

    /**
     * 是否支持当前的事务消息
     *
     * @param arg 这个arg 对应发送事务消息的入参
     * @return true 支持
     */
    @Override
    public boolean support(TransactionArgExt arg) {
        return true;
    }

    /**
     * 操作本地事务
     *
     * @param arg 参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doLocationTransaction(TransactionArgExt arg) {
        // 入库 生成订单记录
        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(Long.valueOf(arg.getData()));
        userOrder.setGmtCreate(LocalDateTime.now());
        userOrder.setGmtModified(userOrder.getGmtCreate());

        log.info("doLocationTransaction -- save entity was :{}", userOrder);
        save(userOrder);
    }
}
