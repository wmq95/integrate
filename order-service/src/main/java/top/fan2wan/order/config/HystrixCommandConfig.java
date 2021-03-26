package top.fan2wan.order.config;

import org.springframework.context.annotation.Configuration;
import top.fan2wan.web.config.AbstractFeignHystrixConcurrencyStrategy;

/**
 * @Author: fanT
 * @Date: 2021/3/26 8:47
 * @Description: config for hystrix
 */
@Configuration
public class HystrixCommandConfig extends AbstractFeignHystrixConcurrencyStrategy {
}
