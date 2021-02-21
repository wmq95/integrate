package top.fan2wan.security.service;

import top.fan2wan.oauth.dto.LoginDTO;
import top.fan2wan.oauth.param.LoginParam;

/**
 * @Author: fanT
 * @Date: 2021/2/21 8:50
 * @Description: service for token
 */
public interface ITokenService {
    /**
     * 用户登录
     *
     * @param param param
     * @return LoginDTO
     */
    LoginDTO login(LoginParam param);
}