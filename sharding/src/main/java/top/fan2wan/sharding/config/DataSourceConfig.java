package top.fan2wan.sharding.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @Author: fanT
 * @Date: 2021/7/29 14:09
 * @Description:
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
    /*@Bean
    @ConfigurationProperties(
            prefix = "spring.datasource.hikari"
    )
    public DataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        return hikariDataSource;
    }*/
}
