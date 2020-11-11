package top.fan2wan.test.controller;


import org.springframework.web.bind.annotation.RestController;
import top.fan2wan.test.dto.UserDTO;
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
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(param.getName());
        userDTO.setPassword(param.getPassword());
        userDTO.setName(param.getName());
        return userService.saveUser(userDTO);
    }
}
