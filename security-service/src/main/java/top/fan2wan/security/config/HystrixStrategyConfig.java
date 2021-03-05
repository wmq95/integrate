package top.fan2wan.security.config;

import org.springframework.context.annotation.Configuration;
import top.fan2wan.web.config.AbstractFeignHystrixConcurrencyStrategy;

/**
 * @Author: fanT
 * @Date: 2021/3/5 9:08
 * @Description: config for hystrixStrategy
 */
@Configuration
public class HystrixStrategyConfig extends AbstractFeignHystrixConcurrencyStrategy {

}
