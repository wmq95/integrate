package top.fan2wan.api.util;

import top.fan2wan.api.exception.BusinessException;
import top.fan2wan.api.exception.MsgCode;

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
    public static void throwException(MsgCode msgCodeEnum) {
        throw new BusinessException(msgCodeEnum);
    }
}
