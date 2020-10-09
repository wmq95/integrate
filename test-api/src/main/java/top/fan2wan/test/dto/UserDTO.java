package top.fan2wan.test.dto;

import com.google.common.base.MoreObjects;
import lombok.Data;

/**
 * @Author: fanT
 * @Date: 2020/10/9 14:29
 * @Description: dto for user
 */
@Data
public class UserDTO {

    private Long id;


    private String name;

    private Long password;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("password", password)
                .toString();
    }
}
