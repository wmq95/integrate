package top.fan2wan.sharding.service;

import top.fan2wan.sharding.entity.TUserOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fanT
 * @since 2021-07-30
 */
public interface ITUserOrderService extends IService<TUserOrder> {

    boolean saveBySharding();
}
