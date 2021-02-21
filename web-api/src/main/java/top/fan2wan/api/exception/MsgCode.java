package top.fan2wan.api.exception;

import top.fan2wan.api.support.IMsgCode;

/**
 * @Author: fanT
 * @Date: 2020/9/30 15:04
 * @Description: msgCode
 */
public enum MsgCode implements IMsgCode {

    /**
     * 成功
     */
    SUCCESS(200, "success"),

    /**
     * token
     */
    TOKEN_INVALID(4001, "token_invalid"),

    /**
     * 参数异常
     */
    PARAM_ERROR(4002, "param_error"),
    /**
     * 失败
     */
    FAILED(500, "failed");

    /**
     * 信息编号
     */
    private final int code;

    /**
     * 提示信息代码
     */
    private final String message;

    /**
     * 构造函数
     *
     * @param code
     * @param message
     */
    MsgCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
