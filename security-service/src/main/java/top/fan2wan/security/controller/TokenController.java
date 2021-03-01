package top.fan2wan.security.controller;

import org.springframework.web.bind.annotation.RestController;
import top.fan2wan.api.dto.Result;
import top.fan2wan.oauth.dto.LoginDTO;
import top.fan2wan.oauth.dto.ValidTokenDTO;
import top.fan2wan.oauth.feign.ITokenFeign;
import top.fan2wan.oauth.param.LoginParam;
import top.fan2wan.security.service.ITokenService;

import javax.validation.Valid;

/**
 * @Author: fanT
 * @Date: 2021/2/21 8:44
 * @Description: controller for token
 */
@RestController
public class TokenController implements ITokenFeign {

    private final ITokenService tokenService;

    public TokenController(ITokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * 用户登录
     *
     * @param param param
     * @return LoginDTO
     */
    @Override
    public Result<LoginDTO> login(@Valid LoginParam param) {
        return Result.success(tokenService.login(param));
    }

    /**
     * 刷新token
     *
     * @return LoginDTO
     */
    @Override
    public Result<LoginDTO> refresh() {
        return tokenService.refresh();
    }

    /**
     * 校验accessToken
     *
     * @param accessToken accessToken
     * @return ValidTokenDTO
     */
    @Override
    public Result<ValidTokenDTO> validToken(String accessToken) {
        return Result.success(tokenService.validToken(accessToken));
    }

}
