package top.fan2wan.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.fan2wan.user.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author fanT
 * @since 2021-03-22
 */
public interface IUserService extends IService<User> {

    Boolean saveTest();
}
