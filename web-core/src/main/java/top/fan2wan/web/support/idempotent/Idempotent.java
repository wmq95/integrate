package top.fan2wan.web.support.idempotent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: fanT
 * @Date: 2021/3/2 13:21
 * @Description: idempotent
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {

    /**
     * 间隔
     *
     * @return ms
     */
    long interval() default 3000;
}
