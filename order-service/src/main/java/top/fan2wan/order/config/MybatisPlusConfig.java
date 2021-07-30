package top.fan2wan.order.config;

import com.zaxxer.hikari.HikariConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import top.fan2wan.mysql.mybatis.AbstractMybatisConfig;

/**
 * @Author: fanT
 * @Date: 2021/3/25 15:04
 * @Description: config for mybatis
 */
@Configuration
@MapperScan("top.fan2wan.order.mapper*")
public class MybatisPlusConfig extends HikariConfig {

}
