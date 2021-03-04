package top.fan2wan.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import top.fan2wan.api.dto.Result;
import top.fan2wan.user.feign.IUserFeignApi;
import top.fan2wan.web.support.idempotent.Idempotent;

/**
 * @Author: fanT
 * @Date: 2021/3/2 10:23
 * @Description: controller for user
 */
@RestController
@Slf4j
public class UserController implements IUserFeignApi {

    /**
     * test
     *
     * @return boolean
     */
    @Override
    @Idempotent
    public Result<Boolean> test() {

        log.info("test -- sleep for 1s");
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        log.info("test -- doSomething success");
        return Result.success(true);
    }
}
