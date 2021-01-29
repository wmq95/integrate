package top.fan2wan.security.manager;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;
import top.fan2wan.security.user.UserBO;

/**
 * @Author: fanT
 * @Date: 2021/1/28 15:20
 * @Description: manager for user
 */
@Service
public class UserManager {
    public UserBO getUserByUsername(String username) {
        // call userService.....
        UserBO bo = new UserBO();
        bo.setPassword(Hashing.sha256().newHasher().putString("123456", Charsets.UTF_8).hash().toString());
        bo.setUsername("fant");
        bo.setPermissionList(Lists.newArrayList("admin"));
        return bo;
    }
}
