package top.fan2wan.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import top.fan2wan.api.dto.Result;
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

    @Override
    public Result<Boolean> saveTest() {

        return Result.success(userService.saveTest());
    }
}
