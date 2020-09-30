package top.fan2wan.test.controller;


import org.springframework.web.bind.annotation.RestController;
import top.fan2wan.commom.util.IdGenerator;
import top.fan2wan.test.entity.User;
import top.fan2wan.test.feign.UserFeignApi;
import top.fan2wan.test.param.SaveUserParam;
import top.fan2wan.test.service.IUserService;

import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author fanT
 * @since 2020-09-30
 */
@RestController
public class UserController implements UserFeignApi {

    final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 新增用户
     *
     * @param param 新增用户param
     * @return Boolean
     */
    @Override
    public Boolean saveUser(@Valid SaveUserParam param) {
        User user = new User();
        user.setName(param.getName());
        user.setUserName(param.getUserName());
        user.setPassword(param.getPassword());
        user.setId(IdGenerator.getId());
        return userService.save(user);
    }
}
