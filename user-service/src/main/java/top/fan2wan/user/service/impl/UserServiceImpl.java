package top.fan2wan.user.service.impl;

import top.fan2wan.user.entity.User;
import top.fan2wan.user.mapper.UserMapper;
import top.fan2wan.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author fanT
 * @since 2021-03-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
