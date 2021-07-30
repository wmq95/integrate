package top.fan2wan.sharding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.fan2wan.common.util.IdGenerator;
import top.fan2wan.sharding.entity.TUserOrder;
import top.fan2wan.sharding.mapper.TUserOrderMapper;
import top.fan2wan.sharding.service.ITUserOrderService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fanT
 * @since 2021-07-30
 */
@Service
@Slf4j
public class TUserOrderServiceImpl extends ServiceImpl<TUserOrderMapper, TUserOrder> implements ITUserOrderService {

    @Override
    public boolean saveBySharding() {

        TUserOrder userOrder = new TUserOrder();
        userOrder.setId(IdGenerator.getId());
        userOrder.setOrderNo("11111");
        userOrder.setAmount(1);
        userOrder.setUserId(1L);
        log.info("userOrder -- id was :{}", userOrder.getId());
        save(userOrder);
        return true;
    }
}
