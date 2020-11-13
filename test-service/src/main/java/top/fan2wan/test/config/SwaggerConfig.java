package top.fan2wan.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import top.fan2wan.web.config.AbstractSwaggerConfig;

/**
 * @Author: fanT
 * @Date: 2020/11/13 16:29
 * @Description: config for swagger
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends AbstractSwaggerConfig {
}
