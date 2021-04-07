package top.fan2wan.user.entity;

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
 * @since 2021-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserIntegral extends Model<UserIntegral> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private Integer num;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String NUM = "num";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
