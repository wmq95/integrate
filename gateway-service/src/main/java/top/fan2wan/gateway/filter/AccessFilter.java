package top.fan2wan.gateway.filter;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.fan2wan.api.exception.MsgCode;
import top.fan2wan.api.util.ExceptionUtil;
import top.fan2wan.gateway.bo.AccessTokenBO;
import top.fan2wan.gateway.constants.StringConstant;
import top.fan2wan.gateway.manager.OauthManager;

import java.util.Arrays;

/**
 * @Author: fanT
 * @Date: 2021/1/11 9:02
 * @Description: filter for access
 * <p>
 * <p>
 * gateway 是怎么实现想 Zuul 一样得前置 后置过滤器的
 * 其实在filter 方法中
 * chain.filter(exchange) 就是前置
 * 之后的.then(XXX)就是后置
 */
@Component
public class AccessFilter implements GlobalFilter, Ordered {
    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);
    private static final String[] publicPath = new String[]{"public", "hello", "login"};

    private final OauthManager oauthManager;

    public AccessFilter(OauthManager oauthManager) {
        this.oauthManager = oauthManager;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final String url = exchange.getRequest().getPath().value();
        logger.info("AccessFilter -- url was :{}", url);
        final boolean needCheck = !Arrays.stream(publicPath).anyMatch(url::contains);

        // 根据token 验证有效性。。权限等。。。。
        final String token = exchange.getRequest().getHeaders().getFirst(StringConstant.TOKEN);
//        logger.info("token was :{}", token);
        AccessTokenBO accessTokenBO = new AccessTokenBO();

        if (needCheck) {
            ExceptionUtil.checkException(StrUtil.isNotBlank(token), MsgCode.TOKEN_WAS_MISSING);
            // 不是公开的接口 需要检验token
            accessTokenBO = oauthManager.validToken(token);
            ExceptionUtil.checkException(accessTokenBO.getIsValid(), MsgCode.TOKEN_INVALID);
        }
        accessTokenBO.setToken(token);
        TokenRefreshContext.set(accessTokenBO);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
