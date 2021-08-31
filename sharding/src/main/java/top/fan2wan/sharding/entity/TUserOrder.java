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
 * @since 2021-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TUserOrder extends Model<TUserOrder> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String orderNo;

    private Long userId;

    private Integer amount;


    public static final String ID = "id";

    public static final String ORDER_NO = "order_no";

    public static final String USER_ID = "user_id";

    public static final String AMOUNT = "amount";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
