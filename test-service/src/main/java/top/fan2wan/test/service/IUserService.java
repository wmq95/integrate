package top.fan2wan.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.fan2wan.test.dto.IUser;
import top.fan2wan.test.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author fanT
 * @since 2020-09-30
 */
public interface IUserService extends IService<User> {

    /**
     * save user
     *
     * @param user user
     * @return boolean
     */
    boolean saveUser(IUser user);

    boolean saveUser_transaction(IUser iUser) throws Exception;
}
