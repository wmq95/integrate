package top.fan2wan.sharding.service;

import top.fan2wan.sharding.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fanT
 * @since 2021-07-29
 */
public interface IUserService extends IService<User> {

    Boolean rollback();
}
