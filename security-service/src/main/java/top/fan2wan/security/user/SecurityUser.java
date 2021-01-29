package top.fan2wan.security.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @Author: fanT
 * @Date: 2021/1/29 14:01
 * @Description: user for security
 */
public class SecurityUser extends User {

    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
