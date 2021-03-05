package top.fan2wan.web.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author: fanT
 * @Date: 2021/3/5 8:43
 * @Description: intercept for feign
 * <p>
 * 这种方法 仅当没有开启hystrix 或者使用的是hystix 的信号量模式才会起作用
 * 不适用于线程隔离模式
 * 如果使用线程隔离的模式 还需要自定义配置hystrix 策略
 */
public abstract class AbstractFeignIntercept implements RequestInterceptor {

    private static Logger logger = LoggerFactory.getLogger(AbstractFeignIntercept.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
//                logger.info("header was :{}, value was :{}", name, values);
                requestTemplate.header(name, values);
            }
        }
        Enumeration<String> bodyNames = request.getParameterNames();
        StringBuffer body = new StringBuffer();
        if (bodyNames != null) {
            while (bodyNames.hasMoreElements()) {
                String name = bodyNames.nextElement();
                String values = request.getParameter(name);
                body.append(name).append("=").append(values).append("&");
            }
        }
        if (body.length() != 0) {
            body.deleteCharAt(body.length() - 1);
            requestTemplate.body(body.toString());
            logger.info("feign interceptor body:{}", body.toString());
        }
    }
}
