package top.fan2wan.user.mapper;

import org.apache.ibatis.annotations.Param;
import top.fan2wan.user.entity.UserIntegral;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fanT
 * @since 2021-04-07
 */
public interface UserIntegralMapper extends BaseMapper<UserIntegral> {

    int updateUserIntegral(@Param("userId") Long userId, @Param("num") int number);
}
