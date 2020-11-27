package top.fan2wan.test.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author fanT
 * @since 2020-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User extends Model<User> {

    /**
     * ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 头像
     */
    private String avatar;

    private Long avatarFileId;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 性别(1：男；2：女)
     */
    private String sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机国家区号，如何中国是86，澳大利亚是61
     */
    private String phoneAreaCode;

    /**
     * 电话
     */
    private String phone;

    /**
     * qq号
     */
    private String qq;

    /**
     * 微信号
     */
    private String weiXin;

    /**
     * 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的 unionid 是唯一的。
     */
    private String wxUnionid;

    /**
     * 支付宝用户的userId
     */
    private String alipay;

    /**
     * 苹果用户唯一id
     */
    private String appleId;

    /**
     * 最近登陆时间
     */
    private String lastLogin;

    /**
     * 最近一次登录ip
     */
    private String lastLoginIp;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 是否有效用户
     */
    private Integer enabled;

    /**
     * 账号是否未过期
     */
    private Integer accountNonExpired;

    /**
     * 密码是否未过期
     */
    private Integer credentialsNonExpired;

    /**
     * 账号是否锁定
     */
    private Integer accountNonLocked;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 最近修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    /**
     * 是否删除（1：删除；0：未删除）
     */
    private Integer isDeleted;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String USER_NAME = "user_name";

    public static final String NICKNAME = "nickname";

    public static final String PASSWORD = "password";

    public static final String SALT = "salt";

    public static final String AVATAR = "avatar";

    public static final String AVATAR_FILE_ID = "avatar_file_id";

    public static final String BIRTHDAY = "birthday";

    public static final String SEX = "sex";

    public static final String EMAIL = "email";

    public static final String PHONE_AREA_CODE = "phone_area_code";

    public static final String PHONE = "phone";

    public static final String QQ = "qq";

    public static final String WEI_XIN = "wei_xin";

    public static final String WX_UNIONID = "wx_unionid";

    public static final String ALIPAY = "alipay";

    public static final String APPLE_ID = "apple_id";

    public static final String LAST_LOGIN = "last_login";

    public static final String LAST_LOGIN_IP = "last_login_ip";

    public static final String REMARKS = "remarks";

    public static final String ENABLED = "enabled";

    public static final String ACCOUNT_NON_EXPIRED = "account_non_expired";

    public static final String CREDENTIALS_NON_EXPIRED = "credentials_non_expired";

    public static final String ACCOUNT_NON_LOCKED = "account_non_locked";

    public static final String GMT_CREATE = "gmt_create";

    public static final String GMT_MODIFIED = "gmt_modified";

    public static final String IS_DELETED = "is_deleted";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
