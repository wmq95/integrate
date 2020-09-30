package top.fan2wan.test.controller;

import org.springframework.web.bind.annotation.RestController;
import top.fan2wan.test.feign.TestFeignApi;

/**
 * @Author: fanT
 * @Date: 2020/9/28 16:22
 * @Description: teset controller
 */
@RestController
public class TestController implements TestFeignApi {
    /**
     * test for service
     *
     * @param name name
     * @return name.toLower
     */
    @Override
    public String toLower(String name) {
        return name.toLowerCase();
    }
}
