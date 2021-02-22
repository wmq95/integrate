package top.fan2wan.web.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.fan2wan.web.constants.HeaderConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: fanT
 * @Date: 2021/2/22 10:54
 * @Description: util for web
 */
public class WebUtil {


    /**
     * 获取 request
     *
     * @return HttpServletRequest current request
     */
    public static final HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

    public static final HttpServletResponse getCurrentResponse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getResponse();
    }

    /**
     * 获取当前用户信息
     *
     * @return current user
     */
    public static String getAccessToken() {
        return getCurrentRequest().getHeader(HeaderConstant.TOKEN);
    }
}
