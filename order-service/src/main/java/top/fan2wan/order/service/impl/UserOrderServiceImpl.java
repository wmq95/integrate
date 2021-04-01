package top.fan2wan.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fan2wan.common.util.IdGenerator;
import top.fan2wan.order.entity.UserOrder;
import top.fan2wan.order.manager.UserManager;
import top.fan2wan.order.mapper.UserOrderMapper;
import top.fan2wan.order.service.IUserOrderService;

import java.time.LocalDateTime;

//import io.seata.spring.annotation.GlobalTransactional;

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
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements IUserOrderService {

    private final UserManager userManager;

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
}
