package top.fan2wan.test.dto;

import com.google.common.base.MoreObjects;
import top.fan2wan.test.entity.User;

/**
 * @Author: fanT
 * @Date: 2020/10/9 14:29
 * @Description: dto for user
 */
//@Data
public class UserAnotherDTO implements IUser {

    private Long id;


    private String name;

    private String password;

    private String userName;

    private String nickName;


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("password", password)
//                .add("userName", userName)
//                .add("nickName", nickName)
                .toString();
    }

    @Override
    public Long getId() {
        return id;
    }

    public UserAnotherDTO setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    public UserAnotherDTO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public UserAnotherDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public String getNickName() {
//        return nickName;
//    }

    public UserAnotherDTO setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public static User transform(IUser user) {

        return new User().setId(user.getId())
                .setPassword(user.getPassword())
                .setName(user.getName())
                .setUserName(user.getUserName());
    }


    public static IUser transform(User user) {

        return new UserAnotherDTO().setId(user.getId())
                .setPassword(user.getPassword())
                .setName(user.getName())
                .setNickName(user.getNickname())
                ;
    }
}
