package top.fan2wan.test.dto;

import com.google.common.base.MoreObjects;
import lombok.Data;
import lombok.experimental.Accessors;
import top.fan2wan.test.entity.User;

/**
 * @Author: fanT
 * @Date: 2020/10/9 14:29
 * @Description: dto for user
 */
@Data
@Accessors(chain = true)
public class UserDTO implements IUser {

    private Long id;


    private String name;

    private String password;

    private String userName;


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("password", password)
                .add("userName", userName)
                .toString();
    }

    public static User transform(IUser user) {

        return new User().setId(user.getId())
                .setPassword(user.getPassword())
                .setName(user.getName())
                .setUserName(user.getUserName());
    }


    public static IUser transform(User user) {

        return new UserDTO().setId(user.getId())
                .setPassword(user.getPassword())
                .setName(user.getName())
                .setUserName(user.getUserName());
    }
}
