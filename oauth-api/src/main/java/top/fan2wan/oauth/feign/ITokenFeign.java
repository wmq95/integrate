package top.fan2wan.oauth.feign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.fan2wan.api.dto.Result;
import top.fan2wan.oauth.dto.LoginDTO;
import top.fan2wan.oauth.param.LoginParam;

import javax.validation.Valid;

/**
 * @Author: fanT
 * @Date: 2021/2/19 9:00
 * @Description: feign for login
 */
@FeignClient("oauth")
@Api(tags = "token管理")
public interface ITokenFeign {

    /**
     * 用户登录
     *
     * @param param param
     * @return LoginDTO
     */
    @RequestMapping(value = "/oauth/login", method = RequestMethod.POST, consumes = {"application/json"})
    @ApiOperation(value = "用户登录", response = LoginDTO.class, notes = "用户登录")
    Result<LoginDTO> login(@Valid @RequestBody LoginParam param);
}
