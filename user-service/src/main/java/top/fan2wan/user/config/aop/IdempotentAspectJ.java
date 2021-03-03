package top.fan2wan.user.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import top.fan2wan.web.support.cache.ICacheService;
import top.fan2wan.web.support.idempotent.AbstractIdempotentAspectJ;

/**
 * @Author: fanT
 * @Date: 2021/3/3 9:43
 * @Description: aspectj
 */
@Configuration
public class IdempotentAspectJ extends AbstractIdempotentAspectJ {

    public IdempotentAspectJ(@Autowired(required = false) ICacheService cacheService) {
        super(cacheService);
    }

    /**
     * 生成缓存key
     *
     * @param point point 切点
     * @return String
     * <p>
     * 可以基于 token 或者切点参数
     */
    @Override
    protected String getCacheKey(ProceedingJoinPoint point) {
        return "test";
    }

    /**
     * 生成缓存value
     *
     * @param point 切点
     * @return String
     * <p>
     * 可以直接使用toString 或者其他
     */
    @Override
    protected String getCacheValue(ProceedingJoinPoint point) {
        return "testValue";
    }
}
