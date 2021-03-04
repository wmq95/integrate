package top.fan2wan.gateway.bo;

import lombok.Data;

/**
 * @Author: fanT
 * @Date: 2021/3/1 9:25
 * @Description: bo for accessToken
 */
@Data
public class AccessTokenBO {

    private Boolean isValid;

    private Boolean isExpired;

    private String token;
}
