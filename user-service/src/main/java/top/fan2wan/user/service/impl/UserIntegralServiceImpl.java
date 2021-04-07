package top.fan2wan.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.fan2wan.user.entity.UserIntegral;
import top.fan2wan.user.mapper.UserIntegralMapper;
import top.fan2wan.user.service.IUserIntegralService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fanT
 * @since 2021-04-07
 */
@Service
public class UserIntegralServiceImpl extends ServiceImpl<UserIntegralMapper, UserIntegral> implements IUserIntegralService {

    @Override
    public Boolean addIntegral(Long userId) {
        getBaseMapper().updateUserIntegral(userId, 1);
        return true;
    }
}
