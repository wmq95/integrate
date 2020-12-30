package top.fan2wan.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: fanT
 * @Date: 2020/12/30 13:27
 * @Description: testController
 *
 * @RefreshScope 当使用nacos做配置中心时，可以实现自动刷新配置文件
 */
@RestController
@RequestMapping("/test")
@RefreshScope
public class TestController {

    @Value("${test.name}")
    String userName;

    @RequestMapping("/getUserName")
    public String getUserName() {
        return userName;
    }
}
