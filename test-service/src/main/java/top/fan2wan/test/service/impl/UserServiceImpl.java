package top.fan2wan.test.service.impl;

import top.fan2wan.test.entity.User;
import top.fan2wan.test.mapper.UserMapper;
import top.fan2wan.test.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author fanT
 * @since 2020-09-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
