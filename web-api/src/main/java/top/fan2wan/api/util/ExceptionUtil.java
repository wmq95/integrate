package top.fan2wan.api.util;

import top.fan2wan.api.exception.BusinessException;
import top.fan2wan.api.support.IMsgCode;

/**
 * @Author: fanT
 * @Date: 2021/2/4 16:35
 * @Description: util for exception
 */
public class ExceptionUtil extends cn.hutool.core.exceptions.ExceptionUtil {

    /**
     * Exception.
     *
     * @param msgCodeEnum the msg code enum
     */
    public static void throwException(IMsgCode msgCodeEnum) {
        throw new BusinessException(msgCodeEnum);
    }

    /**
     * 语法糖 check
     *
     * @param flag    flag
     * @param msgCode msgCode
     */
    public static void checkException(Boolean flag, IMsgCode msgCode) {
        if (null == flag || !flag) {
            throwException(msgCode);
        }
    }
}
