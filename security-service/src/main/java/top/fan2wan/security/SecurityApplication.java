package top.fan2wan.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: fanT
 * @Date: 2021/1/26 8:45
 * @Description: application for security
 *
 * 其实 security 就是一个个的拦截器
 *
 * 1 认证 : authentication
 *  a)关键的filter UsernamePasswordAuthenticationFilter
 *  b)会构建userNamePasswordAuthenticationToken
 *      里面保存了登录传入的用户名密码等信息 标记成为认证用于之后调用userDetail去数据库查询
 *  c)最后调用authenticationManager认证默认实现是ProviderManager
 *  d)认证成功之后 调用SecurityContextHolder 保存认证之后的userNamePasswordAuthenticationToken
 *      还有rememberMe等操作
 *
 * 2 权限访问流程:
 *  a)核心filter :ExceptionTranslationFilter 和 FilterSecurityInterceptor
 *  b)ExceptionTranslationFilter:异常处理 前端异常 直接放行,如果是后端抛出的异常AccessDeniedException,AuthenticationException等等做处理
 *  C)FilterSecurityInterceptor 判断请求是否有权限---InterceptorStatusToken token = super.beforeInvocation(fi);
 *
 *
 * SecurityContextPersistenceFilter: 一个最前置的filter 会检查当前session有没有authentication对象,
 * 有的话返回,最后会把authentication和session绑定在一起
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"top.fan2wan.*.feign"})
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
}
