package top.fan2wan.user.config;

import com.zaxxer.hikari.HikariConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: fanT
 * @Date: 2021/4/7 15:18
 * @Description: config for mybatisPlus
 */
@Configuration
@MapperScan("top.fan2wan.user.mapper*")
public class MybatisPlusConfig extends HikariConfig {
}
