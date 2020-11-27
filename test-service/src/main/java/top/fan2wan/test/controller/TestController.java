package top.fan2wan.test.controller;

import org.dozer.DozerBeanMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fan2wan.common.util.IdGenerator;
import top.fan2wan.test.dto.IUser;
import top.fan2wan.test.dto.UserDTO;
import top.fan2wan.test.entity.User;
import top.fan2wan.test.feign.TestFeignApi;
import top.fan2wan.test.util.RedisUtil;

/**
 * @Author: fant2
 * @Date: 2020/9/28 16:22
 * @Description: teset controller
 */
@RestController
public class TestController implements TestFeignApi {

    final DozerBeanMapper mapper;
    final RedisUtil redisUtil;

    public TestController(DozerBeanMapper mapper, RedisUtil redisUtil) {
        this.mapper = mapper;
        this.redisUtil = redisUtil;
    }

    /**
     * test for service
     *
     * @param name name
     * @return name.toLower
     */
    @Override
    public String toLower(String name) {
        return name.toLowerCase();
    }

    @RequestMapping("testService/index/dozer")
    public IUser dozerTest() {

        User user = new User();
        user.setId(IdGenerator.getId());
        user.setName("fant2");
        user.setPassword("123456");

        return mapper.map(user, UserDTO.class);
    }

    @RequestMapping("testService/index/redis/set")
    public Boolean set() {
        return redisUtil.set("fant2", new User().setName("fant2").setId(IdGenerator.getId()));
    }

    @RequestMapping("testService/index/redis/get")
    public Object get() {
        return redisUtil.get("fant2");
    }

    @RequestMapping("testService/index/redis/getUser")
    public User getUser() {
        //会报错 因为value 序列化设置问题
        return (User) redisUtil.get("fant2");
    }

    @RequestMapping("testService/index/redis/getUserByDozer")
    public User getUserByDozer() {
        return mapper.map(redisUtil.get("fant2"),User.class);
    }
}
