package top.fan2wan.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import top.fan2wan.api.dto.Result;
import top.fan2wan.security.user.SecurityUser;
import top.fan2wan.security.user.UserBO;
import top.fan2wan.security.util.JwtUtil;
import top.fan2wan.security.util.ResponseUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @Author: fanT
 * @Date: 2021/1/29 14:26
 * @Description: filter for login
 */
@Slf4j
public class UserLoginFilter extends UsernamePasswordAuthenticationFilter {
    private static final String TOKEN_HEAD = "token";

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public UserLoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login","POST"));
    }

    //1 获取表单提交用户名和密码
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        //获取表单提交数据
        try {
            UserBO user = new ObjectMapper().readValue(request.getInputStream(), UserBO.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),
                    new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //2 认证成功调用的方法
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        log.info("successfulAuthentication");
        //认证成功，得到认证成功之后用户信息
        SecurityUser user = (SecurityUser)authResult.getPrincipal();
        //根据用户名生成token
        String token = jwtUtil.generatorJwt(user.getUsername());

        //返回token
        Map<String, String> tokenMap = Maps.newConcurrentMap();
        tokenMap.put(TOKEN_HEAD, token);
        ResponseUtil.out(response, Result.success(tokenMap));
    }

    //3 认证失败调用的方法
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        log.info("unsuccessfulAuthentication....");
        ResponseUtil.out(response, Result.error());
    }
}
