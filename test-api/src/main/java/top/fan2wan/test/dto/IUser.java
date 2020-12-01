package top.fan2wan.test.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: fanT
 * @Date: 2020/10/15 8:51
 * @Description: interface for user
 */
public interface IUser {

    /**
     * getId
     *
     * @return userId
     */
    @ApiModelProperty(value = "用户id")
    Long getId();

    /**
     * getName
     *
     * @return name
     */
    @ApiModelProperty(value = "名称")
    String getName();

    /**
     * getUserName
     *
     * @return userName
     */
    @ApiModelProperty(value = "用户名")
    String getUserName();

    /**
     * getPassword
     *
     * @return password
     */
    @ApiModelProperty(value = "密码")
    String getPassword();
}
