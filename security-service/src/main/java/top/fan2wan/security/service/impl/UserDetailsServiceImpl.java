package top.fan2wan.security.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.fan2wan.security.manager.UserManager;
import top.fan2wan.security.user.SecurityUser;
import top.fan2wan.security.user.UserBO;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: fanT
 * @Date: 2021/1/28 15:16
 * @Description: impl for userDetails
 * @Service("userDetailsService") 声明为security userDetailsService
 * <p>
 * 只需要重写根据用户名获取用户 然后返回对象 user 对象
 * 这儿可以做一些其他操作 缓存等
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserManager userManager;

    public UserDetailsServiceImpl(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loadUserByUsername -- currentUsername was :{}", username);

        UserBO userBO = userManager.getUserByUsername(username);
        if (Objects.isNull(userBO)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return new SecurityUser(userBO.getUsername(), userBO.getPassword(), userBO.getPermissionList().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()));
    }
}
