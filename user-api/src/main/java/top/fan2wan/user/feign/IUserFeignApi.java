package top.fan2wan.user.feign;

import io.swagger.annotations.ApiModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.fan2wan.api.dto.Result;

/**
 * @Author: fanT
 * @Date: 2021/3/2 10:22
 * @Description: feignApi for user
 */
@FeignClient("user")
@ApiModel("用户管理")
public interface IUserFeignApi {

    /**
     * test
     *
     * @return boolean
     */
    @RequestMapping(value = "/user/test", method = RequestMethod.GET)
    Result<Boolean> test();

    /**
     * test for save
     *
     * @return true
     */
    @RequestMapping(value = "/user/saveTest", method = RequestMethod.POST)
    Result<Boolean> saveTest();
}
