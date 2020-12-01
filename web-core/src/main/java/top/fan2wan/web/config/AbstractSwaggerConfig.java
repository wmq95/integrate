package top.fan2wan.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: fanT
 * @Date: 2020/11/13 16:24
 * @Description: config for swagger
 */
public abstract class AbstractSwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.fan2wan"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("integration for Swagger")
                        .description("integration for Swagger")
                        .version("1.0")
                        .contact(new Contact("fanT","https://github.com/wmq95/integrate","1282904462@qq.com"))
                        .license("fanT")
                        .licenseUrl("https://github.com/wmq95/integrate")
                        .build());
    }
}
