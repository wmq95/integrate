package top.fan2wan.order.service.impl;

import lombok.extern.slf4j.Slf4j;
import top.fan2wan.common.util.IdGenerator;
import top.fan2wan.order.entity.UserOrder;
import top.fan2wan.order.mapper.UserOrderMapper;
import top.fan2wan.order.service.IUserOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements IUserOrderService {


    @Override
    public Boolean saveTest() {
        UserOrder userOrder = new UserOrder();
        userOrder.setId(IdGenerator.getId());
        userOrder.setGmtCreate(LocalDateTime.now());
        userOrder.setGmtModified(userOrder.getGmtCreate());
        userOrder.setUserId(userOrder.getId());
        log.info("saveTest -- save entity was :{}", userOrder);

        return save(userOrder);
    }
}
