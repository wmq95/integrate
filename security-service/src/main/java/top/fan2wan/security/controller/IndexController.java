package top.fan2wan.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: fanT
 * @Date: 2021/1/29 14:58
 * @Description: index controller
 */
@RestController
public class IndexController {

    @RequestMapping("/index/hello")
    public String hello() {
        return "hello";
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @RequestMapping("/index/acl")
    public String acl() {
        return "acl";
    }
}
