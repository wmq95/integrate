package top.fan2wan.testService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import top.fan2wan.common.util.IdGenerator;
import top.fan2wan.test.TestApplication;
import top.fan2wan.test.dto.UserDTO;
import top.fan2wan.test.service.IUserService;

/**
 * @Author: fanT
 * @Date: 2020/10/15 10:59
 * @Description: test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class)
@WebAppConfiguration
public class TestService {

    @Autowired
    IUserService userService;

    @Test
    public void test_save_transaction() throws Exception {
        userService.saveUser_transaction(getUserDTO());
    }

    private UserDTO getUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(IdGenerator.getId().toString());
        userDTO.setUserName(userDTO.getName());
        userDTO.setPassword(userDTO.getName());
        return userDTO;
    }
}
