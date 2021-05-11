package top.fan2wan.order.constant;

import top.fan2wan.api.support.IMsgCode;

/**
 * @Author: fanT
 * @Date: 2021/5/11 9:17
 * @Description: orderCode
 */
public enum OrderCode implements IMsgCode {

    /**
     * 商品售罄
     */
    SOLD_OUT(200,"SOLD_OUT");

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
    OrderCode(int code, String message) {
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
