package top.fan2wan.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fan2wan.common.util.IdGenerator;
import top.fan2wan.user.entity.User;
import top.fan2wan.user.mapper.UserMapper;
import top.fan2wan.user.service.IUserService;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author fanT
 * @since 2021-03-22
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveTest() {
        User user = new User();
        user.setPassword("123213213");
        user.setId(IdGenerator.getId());
        user.setName("distribute");
        user.setUserName("distribute");
        user.setGmtCreate(LocalDateTime.now());
        user.setGmtModified(user.getGmtCreate());
        log.info("saveTest -- entity was :{}", user);

        return save(user);
    }
}
