package top.fan2wan.security.manager;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.fan2wan.security.user.UserBO;
import top.fan2wan.user.feign.IUserFeignApi;

/**
 * @Author: fanT
 * @Date: 2021/1/28 15:20
 * @Description: manager for user
 */
@Service
@Slf4j
public class UserManager {

    private final IUserFeignApi userFeignApi;

    public UserManager(IUserFeignApi userFeignApi) {
        this.userFeignApi = userFeignApi;
    }

    public UserBO getUserByUsername(String username) {
        // call userService.....
        UserBO bo = new UserBO();
        bo.setPassword(Hashing.sha256().newHasher().putString("123456", Charsets.UTF_8).hash().toString());
        bo.setUsername("fant");
        bo.setPermissionList(Lists.newArrayList("admin"));
        return bo;
    }

    /**
     * 测试feign 重试机制
     * 首先 feign 和ribbon 都有重试
     * feignClient 默认配置 Retryer.NEVER_RETRY
     * 当我们引入openFeign 的时候 feignClient的配置其实没有使用
     * 用到了ribbon的配置在FeignLoadBalancer中
     * <p>
     * 关于配置重试的几点
     * OKToRetryOnAllOperations:默认为true 建议设为false true 的话 true 是给所以方法进行重试 false 只会给Get方法进行重试
     * ribbon 超时时间和hystrix 熔断时间 按理熔断时间应该最起码是ribbon超时时间的2倍 不然的话 第二次重试会出现熔断
     * <p>
     * 如果偏偏开启了对于post put 进行重试
     * 需要确保接口的幂等
     * 一种借用redis 对于第二次及以后的都做屏蔽
     * 一种 可以借助于数据库的乐观锁
     */
    public void testFeign() {
        log.info("call userFeign for test");
        userFeignApi.test().result();
        log.info("call success");
    }
}
