package top.fan2wan.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @Author: fanT
 * @Date: 2021/7/29 14:00
 * @Description:
 */
@SpringBootApplication
@MapperScan("top.fan2wan.sharding.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
