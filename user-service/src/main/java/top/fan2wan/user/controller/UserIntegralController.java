package top.fan2wan.user.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.fan2wan.api.dto.Result;
import top.fan2wan.user.feign.IUserIntegralFeignApi;
import top.fan2wan.user.service.IUserIntegralService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fanT
 * @since 2021-04-07
 */
@RestController
@AllArgsConstructor
public class UserIntegralController implements IUserIntegralFeignApi{

    private final IUserIntegralService userIntegralService;

    /**
     * addIntegral
     *
     * @param userId userId
     * @return boolean
     */
    @Override
    public Result<Boolean> addIntegral(Long userId) {

        return Result.success(userIntegralService.addIntegral(userId));
    }
}
