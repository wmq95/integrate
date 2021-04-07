package top.fan2wan.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.fan2wan.order.entity.UserOrder;

/**
 * <p>
 * order 服务类
 * </p>
 *
 * @author fanT
 * @since 2021-03-25
 */
public interface IUserOrderService extends IService<UserOrder> {

    Boolean saveTest();

    Boolean saveWithRollback();

    Boolean placeOrder(Long userId);
}
