package top.fan2wan.oauth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: fanT
 * @Date: 2021/3/1 9:30
 * @Description: dto for token to valid
 */
@Data
@ApiModel("accessToken校验dto")
public class ValidTokenDTO {

    @ApiModelProperty(value = "是否通过校验")
    private Boolean isValid;

    @ApiModelProperty(value = "是否过期")
    private Boolean isExpired;
}
