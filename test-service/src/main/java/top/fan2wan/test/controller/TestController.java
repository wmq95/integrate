package top.fan2wan.test.controller;

import org.dozer.DozerBeanMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fan2wan.common.util.IdGenerator;
import top.fan2wan.test.dto.IUser;
import top.fan2wan.test.dto.UserDTO;
import top.fan2wan.test.entity.User;
import top.fan2wan.test.feign.TestFeignApi;

/**
 * @Author: fanT
 * @Date: 2020/9/28 16:22
 * @Description: teset controller
 */
@RestController
public class TestController implements TestFeignApi {

    final DozerBeanMapper mapper;

    public TestController(DozerBeanMapper mapper) {
        this.mapper = mapper;
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
        user.setName("fanT");
        user.setPassword("123456");

        return mapper.map(user, UserDTO.class);
    }
}
