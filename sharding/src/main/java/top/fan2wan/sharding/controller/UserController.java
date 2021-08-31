package top.fan2wan.sharding.controller;


import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fan2wan.sharding.entity.User;
import top.fan2wan.sharding.service.IUserService;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author fanT
 * @since 2021-07-29
 */
@RestController
@RequestMapping("/sharding/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/list")
    public List<User> list() {
        HintManager.getInstance().setMasterRouteOnly();
        return userService.list();
    }

    @RequestMapping("/rollback")
    public Boolean rollback() {
        return userService.rollback();
    }
}
