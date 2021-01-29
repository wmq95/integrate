package top.fan2wan.security.user;

import lombok.Data;

import java.util.List;

/**
 * @Author: fanT
 * @Date: 2021/1/29 13:56
 * @Description: bo for user
 */
@Data
public class UserBO {

    private String username;

    private String password;

    private List<String> permissionList;
}
