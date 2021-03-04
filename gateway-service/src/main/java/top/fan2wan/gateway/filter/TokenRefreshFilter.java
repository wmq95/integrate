package top.fan2wan.gateway.filter;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.fan2wan.gateway.bo.AccessTokenBO;
import top.fan2wan.gateway.manager.OauthManager;

import java.util.Objects;

/**
 * @Author: fanT
 * @Date: 2021/3/1 10:51
 * @Description: filter for token to refresh
 */
@Component
@Slf4j
public class TokenRefreshFilter implements GlobalFilter, Ordered {
    private final static int POST_FILTER_ORDER = 100;

    private final OauthManager oauthManager;

    public TokenRefreshFilter(OauthManager oauthManager) {
        this.oauthManager = oauthManager;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        AccessTokenBO bo = TokenRefreshContext.get();
        try {
            if (Objects.nonNull(bo.getIsExpired()) && bo.getIsExpired()) {
                //access_token 过期 去刷新token
                final String token = oauthManager.refreshToken(bo.getToken());
                if (StrUtil.isNotBlank(token)) {
                    log.info("TokenRefreshFilter -- refresh token success, add header");
                    exchange.getResponse().getHeaders().add("token", token);
                }
            }
        } catch (Exception e) {
            log.error("TokenRefreshFilter -- error was :{}", e.getMessage());
        } finally {
            // remove
            TokenRefreshContext.clear();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return POST_FILTER_ORDER;
    }
}
