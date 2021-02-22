package top.fan2wan.security.enums;

/**
 * @Author: fanT
 * @Date: 2021/2/22 10:38
 * @Description: enum for grantType
 */
public enum OauthGrantTypeEnum {
    /**
     * 密码模式
     */
    PASSWORD("password"),

    /**
     * 刷新access_token 这个因为security oauth 把刷新token 认为是一种模式了
     */
    REFRESH_TOKEN("refresh_token");


    private String name;


    OauthGrantTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
