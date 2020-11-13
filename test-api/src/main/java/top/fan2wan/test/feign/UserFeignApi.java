package top.fan2wan.test.feign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.fan2wan.test.dto.IUser;
import top.fan2wan.test.param.SaveUserParam;

import javax.validation.Valid;

/**
 * @Author: fanT
 * @Date: 2020/9/30 13:56
 * @Description: feignApi for user
 */
@FeignClient("test")
@Api(tags = "用户feign")
public interface UserFeignApi {

    /**
     * 新增用户
     *
     * @param param 新增用户param
     * @return Boolean
     */
    @RequestMapping(value = "/testService/user/saveUser", method = RequestMethod.POST, consumes = {"application/json"})
    @ApiOperation(value = "新增用户",response = Boolean.class, notes = "新增用户")
    Boolean saveUser(@RequestBody @Valid SaveUserParam param);
}
