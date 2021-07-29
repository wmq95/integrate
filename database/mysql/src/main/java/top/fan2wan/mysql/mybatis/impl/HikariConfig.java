package top.fan2wan.mysql.mybatis.impl;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import top.fan2wan.mysql.mybatis.AbstractMybatisConfig;

import javax.sql.DataSource;


/**
 * @Author: fanT
 * @Date: 2021/7/29 14:45
 * @Description: user HakariDataSource
 */
public class HikariConfig extends AbstractMybatisConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        return hikariDataSource;
    }
}
