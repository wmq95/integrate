package top.fan2wan.test.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.fan2wan.api.dto.Result;
import top.fan2wan.test.dto.IUser;
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

    @Override
    public Result<IUser> getUserById(Long id) {
        return Result.success(userService.getUserWithCache(id));
    }


    /**
     * controller 这儿定义的返回IUser接口
     * 但是实际操作之后 会根据返回值的具体实现 返回不同的结果
     *
     * 这样貌似有很多操作空间。。。但是 没遇过
     */
    @ApiOperation(value = "查询用户",response = IUser.class, notes = "查询用户")
    @RequestMapping(value = "/testService/user/getUser", method = RequestMethod.GET)
    public IUser getUser() {
        return UserDTO.transform(userService.getById(530830763772616704L));
    }
}
