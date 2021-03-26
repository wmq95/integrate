package top.fan2wan.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: fanT
 * @Date: 2021/3/25 14:45
 * @Description: application for order
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"top.fan2wan.*.feign"})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
