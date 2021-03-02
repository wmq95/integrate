package top.fan2wan.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fan2wan.security.manager.UserManager;

/**
 * @Author: fanT
 * @Date: 2021/1/29 14:58
 * @Description: index controller
 */
@Slf4j
@RestController
public class IndexController {

    private final UserManager userManager;

    public IndexController(UserManager userManager) {
        this.userManager = userManager;
    }

    @RequestMapping("/index/hello")
    public String hello() {
        userManager.testFeign();
        return "hello";
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @RequestMapping("/index/acl")
    public String acl() {
        return "acl";
    }

    @GetMapping(value = "/")
    public String index() {
        log.info(SecurityContextHolder.getContext().getAuthentication().toString());
        return "Welcome " + SecurityContextHolder.getContext().getAuthentication();
    }
}
