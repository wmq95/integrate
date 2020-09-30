package top.fan2wan.test.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.fan2wan.test.param.SaveUserParam;

import javax.validation.Valid;

/**
 * @Author: fanT
 * @Date: 2020/9/30 13:56
 * @Description: feignApi for user
 */
@FeignClient("test")
public interface UserFeignApi {

    /**
     * 新增用户
     *
     * @param param 新增用户param
     * @return Boolean
     */
    @RequestMapping(value = "/testService/user/saveUser", method = RequestMethod.POST, consumes = {"application/json"})
    Boolean saveUser(@RequestBody @Valid SaveUserParam param);
}
