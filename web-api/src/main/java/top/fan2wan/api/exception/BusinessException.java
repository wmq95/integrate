package top.fan2wan.api.exception;

import top.fan2wan.api.support.IMsgCode;

/**
 * @Author: fanT
 * @Date: 2020/9/30 14:55
 * @Description: businessException
 */
public class BusinessException extends RuntimeException {

    int code;

    String message;


    public BusinessException(IMsgCode msgCode) {
        this.code = msgCode.getCode();
        this.message = msgCode.getMessage();
    }

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
