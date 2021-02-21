package top.fan2wan.oauth.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: fanT
 * @Date: 2021/2/19 9:03
 * @Description: param for login
 */
@Data
@ApiModel("登录param")
public class LoginParam {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String grantType;

    @NotBlank
    private String clientId;

    @NotBlank
    private String clientSecret;

    @NotBlank
    private String scope;
}
