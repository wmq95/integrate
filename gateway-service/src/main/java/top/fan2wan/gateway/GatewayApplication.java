package top.fan2wan.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: fanT
 * @Date: 2020/12/30 13:24
 * @Description: gatewayApplication
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"top.fan2wan.*.feign"})
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
