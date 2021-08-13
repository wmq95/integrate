package top.fan2wan.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import top.fan2wan.mysql.mybatis.impl.HikariMybatisConfig;

/**
 * @Author: fanT
 * @Date: 2021/4/7 15:18
 * @Description: config for mybatisPlus
 */
@Configuration
@MapperScan("top.fan2wan.user.mapper*")
public class MybatisPlusConfig extends HikariMybatisConfig {
}
