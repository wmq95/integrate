package top.fan2wan.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: fanT
 * @Date: 2021/1/11 9:02
 * @Description: filter for access
 */
@Component
public class AccessFilter implements GlobalFilter, Ordered {
    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("AccessFilter -- do filter");
        // 根据token 验证有效性。。权限等。。。。
        String originHeader = exchange.getRequest().getHeaders().getFirst("user");
        logger.info("header was :{}", originHeader);
//        添加header 方法
//        ServerHttpRequest request = exchange.getRequest().mutate()
//                .headers(httpHeaders -> {
//                    httpHeaders.add("user", "fanT");
//                })
//                .build();
//        logger.info("add header");
//        return chain.filter(exchange.mutate().request(request).build());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
