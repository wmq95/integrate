package top.fan2wan.user.controller;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import top.fan2wan.api.dto.Result;
import top.fan2wan.common.util.IdGenerator;
import top.fan2wan.user.entity.User;
import top.fan2wan.user.feign.IUserFeignApi;
import top.fan2wan.user.service.IUserService;
import top.fan2wan.web.support.idempotent.Idempotent;
import top.fan2wan.web.util.WebUtil;

/**
 * @Author: fanT
 * @Date: 2021/3/2 10:23
 * @Description: controller for user
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController implements IUserFeignApi {

    final private IUserService userService;

    /**
     * test
     *
     * @return boolean
     */
    @Override
    @Idempotent
    public Result<Boolean> test() {

        log.info("test -- token was :{}", WebUtil.getAccessToken());
        /*log.info("test -- sleep for 1s");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("test -- doSomething success");*/
        return Result.success(true);
    }

    @ApiIgnore
    @RequestMapping("/user/save")
    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    public Boolean save() {
        User user = new User();
        user.setPassword("123213213");
        user.setId(IdGenerator.getId());
        user.setName("distribute");
        user.setUserName("distribute");
        userService.save(user);
        /**
         * TODO
         * 没有undo 日志 不知道是不是因为没有跨服务调用
         * 加入另一个模块试一下
         */
        return true;
    }
}
