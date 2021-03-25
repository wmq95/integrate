package top.fan2wan.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: fanT
 * @Date: 2021/3/25 14:45
 * @Description: application for order
 */
@SpringBootApplication
@MapperScan("top.fan2wan.order.mapper*")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
