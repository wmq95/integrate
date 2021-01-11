package top.fan2wan.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.fan2wan.test.feign.TestFeignApi;

import java.util.concurrent.*;

/**
 * @Author: fanT
 * @Date: 2021/1/8 13:29
 * @Description: filter for testService
 */
//@Component
public class TestServiceFilter implements GlobalFilter, Ordered {

    private static Logger logger = LoggerFactory.getLogger(TestServiceFilter.class);
    private static ExecutorService executorService = new ThreadPoolExecutor(1, 2, 1000,
            TimeUnit.MILLISECONDS, new LinkedBlockingDeque());
    private final TestFeignApi testFeignApi;

    public TestServiceFilter(TestFeignApi testFeignApi) {
        this.testFeignApi = testFeignApi;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("filter -- call testFeignApi ");
        // 会超时
//        String name = testFeignApi.testForName("fanT");
//        logger.info("testForName success.....");

//        尽管使用了completableFuture 本质上最后还是调用了feign 还是回收feign的readTimeOut 时间限制
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> testFeignApi.testForName("fanT"),
                executorService);
        logger.info("do something else ");

        try {
            Thread.sleep(3000);
            logger.info("completableFuture result was :{}", completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
