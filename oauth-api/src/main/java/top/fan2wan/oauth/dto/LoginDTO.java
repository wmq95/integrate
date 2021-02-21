package top.fan2wan.oauth.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: fanT
 * @Date: 2021/2/19 10:30
 * @Description: dto for login
 */
@Data
@ApiModel("登录dto")
public class LoginDTO {

    private String accessToken;
}
