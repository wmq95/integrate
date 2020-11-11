package top.fan2wan.test.dto;

/**
 * @Author: fanT
 * @Date: 2020/10/15 8:51
 * @Description: interface for user
 */
public interface IUser {

    /**
     * @return userId
     */
    Long getId();

    /**
     * @return name
     */
    String getName();

    /**
     * @return userName
     */
    String getUserName();

    /**
     * @return password
     */
    String getPassword();
}
