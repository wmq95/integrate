package top.fan2wan.test.param;

import com.google.common.base.MoreObjects;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: fanT
 * @Date: 2020/9/30 13:58
 * @Description: param for user to save
 */
@Data
public class SaveUserParam {

    @NotBlank(message = "用户名不可为空")
    private String userName;

    @NotBlank(message = "用户姓名不可为空")
    private String name;

    @NotBlank(message = "账号密码不可为空")
    private String password;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("userName", userName)
                .add("name", name)
                .add("password", password)
                .toString();
    }
}
