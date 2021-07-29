package top.fan2wan.sharding.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author fanT
 * @since 2021-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer age;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String AGE = "age";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
