package top.fan2wan.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: fanT
 * @Date: 2020/9/29 14:13
 * @Description: testApplication
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
