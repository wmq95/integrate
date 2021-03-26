package top.fan2wan.order.manager;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.fan2wan.user.feign.IUserFeignApi;

/**
 * @Author: fanT
 * @Date: 2021/3/26 8:52
 * @Description: manager for user
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserManager {

    private final IUserFeignApi userFeignApi;

    public Boolean saveTest() {

        userFeignApi.saveTest();
        return true;
    }
}
