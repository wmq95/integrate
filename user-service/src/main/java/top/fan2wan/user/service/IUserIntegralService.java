package top.fan2wan.user.service;

import top.fan2wan.user.entity.UserIntegral;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fanT
 * @since 2021-04-07
 */
public interface IUserIntegralService extends IService<UserIntegral> {

    Boolean addIntegral(Long userId);
}
