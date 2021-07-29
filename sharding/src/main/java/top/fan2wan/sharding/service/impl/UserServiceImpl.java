package top.fan2wan.sharding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fan2wan.sharding.entity.User;
import top.fan2wan.sharding.mapper.UserMapper;
import top.fan2wan.sharding.service.IUserService;

import java.util.Random;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fanT
 * @since 2021-07-29
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean rollback() {
        User user = new User();
        Random r = new Random();
        user.setId(r.nextInt(110));
        user.setAge(r.nextInt(30));
        user.setName(UUID.randomUUID().toString().substring(0,5));
        save(user);

        log.info("user --save success..");
        int i = 1 / 0;
        return i == 0;
    }
}
