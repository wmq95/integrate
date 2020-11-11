package top.fan2wan.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.fan2wan.api.exception.BusinessException;
import top.fan2wan.api.exception.MsgCode;
import top.fan2wan.common.util.IdGenerator;
import top.fan2wan.test.dto.IUser;
import top.fan2wan.test.dto.UserDTO;
import top.fan2wan.test.entity.User;
import top.fan2wan.test.mapper.UserMapper;
import top.fan2wan.test.service.IUserService;

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

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    final DozerBeanMapper mapper;

    public UserServiceImpl(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean saveUser(IUser user) {
        logger.info("UserServiceImpl -- saveUser, user was :{}", user);
        User mapUser = mapper.map(user, User.class);
        logger.info("dozer map user, user was :{}", mapUser);
        User entity = UserDTO.transform(user);
        entity.setId(IdGenerator.getId());
        return save(entity);
    }

    @Override
    @Transactional
    public boolean saveUser_transaction(IUser iUser) throws Exception {
       /* saveUser_private(iUser);

        saveUser_public(iUser);

        int i = 1/0;*/
        return saveUser_public_throwException(iUser);
    }

    private boolean saveUser_public_throwException(IUser iUser) throws Exception {
        User entity = UserDTO.transform(iUser);
        entity.setId(IdGenerator.getId());
        save(entity);
        throw new Exception("");
//        return false;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public boolean saveUser_public(IUser iUser) {
        User entity = UserDTO.transform(iUser);
        entity.setId(IdGenerator.getId());
        save(entity);
//        logger.info("saveUser_public -- save success");
//        int i = 1 / 0;

        return true;
    }


    private boolean saveUser_private(IUser iUser) {
        User entity = UserDTO.transform(iUser);
        entity.setId(IdGenerator.getId());
        save(entity);
        logger.info("saveUser_private -- save success");
//        int a = 1 / 0;
        return true;
    }
}
