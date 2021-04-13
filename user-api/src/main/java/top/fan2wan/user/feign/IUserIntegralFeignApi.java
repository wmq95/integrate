package top.fan2wan.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import top.fan2wan.api.dto.Result;

/**
 * @Author: fanT
 * @Date: 2021/4/7 13:29
 * @Description: feign for userIntegral
 */
@FeignClient("user")
public interface IUserIntegralFeignApi {

    /**
     * addIntegral
     *
     * @param userId userId
     * @return boolean
     */
    @RequestMapping(value = "/user/addIntegral")
    Result<Boolean> addIntegral(@RequestParam("userId") Long userId);
}
