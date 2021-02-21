package top.fan2wan.security.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import top.fan2wan.api.dto.Result;
import top.fan2wan.api.exception.MsgCode;
import top.fan2wan.security.util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: fanT
 * @Date: 2021/2/5 13:38
 * @Description: entryPoint for oauth
 */
@Component
@Slf4j
public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws ServletException {
        Throwable cause = authException.getCause();
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        if (cause instanceof OAuth2AccessDeniedException) {
            // 资源权限不足
            log.info("token was expired");
            log.info("error was :{}", cause.getMessage());
            ResponseUtil.out(response, Result.error(MsgCode.TOKEN_INVALID));
        } else if (cause == null || cause instanceof InvalidTokenException) {
            // 未带token或token无效
            log.info("error was :{}", cause.getMessage());
            // cause == null 一般可能是未带token
            ResponseUtil.out(response, Result.error(MsgCode.TOKEN_INVALID));
        }
    }
}
