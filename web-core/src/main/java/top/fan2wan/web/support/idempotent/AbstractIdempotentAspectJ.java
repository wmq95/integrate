package top.fan2wan.web.support.idempotent;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.fan2wan.api.dto.Result;
import top.fan2wan.api.exception.BusinessException;
import top.fan2wan.api.exception.MsgCode;
import top.fan2wan.web.support.cache.DefaultCacheService;
import top.fan2wan.web.support.cache.ICacheService;
import top.fan2wan.web.util.HttpUtil;
import top.fan2wan.web.util.WebUtil;

import java.util.Objects;

/**
 * @Author: fanT
 * @Date: 2021/3/2 13:25
 * @Description: aspectj for idempotent
 */
@Aspect
public abstract class AbstractIdempotentAspectJ {

    private static Logger logger = LoggerFactory.getLogger(AbstractIdempotentAspectJ.class);

    private final ICacheService cacheService;

    public AbstractIdempotentAspectJ(ICacheService cacheService) {
        if (cacheService == null) {
            // user defaultCacheService
            cacheService = new DefaultCacheService();
        }
        this.cacheService = cacheService;
    }

    @Pointcut("@annotation(top.fan2wan.web.support.idempotent.Idempotent)")
    public void idempotentPointCut() {
    }

    @Around(value = "idempotentPointCut()")
    public Object doAround(ProceedingJoinPoint point) {

        MethodSignature signature = (MethodSignature) point.getSignature();
        Idempotent annotation = signature.getMethod().getAnnotation(Idempotent.class);
        // 过期时间
        long interval = annotation.interval();

        final String key = getCacheKey(point);
        final String value = getCacheValue(point);

        logger.info("doAround -- around");
        Object tmp = cacheService.get(key);
        if (Objects.nonNull(tmp)) {
            // 存在缓存 返回请勿操作
            HttpUtil.out(WebUtil.getCurrentResponse(), Result.error(MsgCode.REPEAT_OPERATION));
            return null;
        }

        if (!cacheService.put(key, value, interval)) {
            HttpUtil.out(WebUtil.getCurrentResponse(), Result.error(MsgCode.FAILED));
            return null;
        }

        tmp = cacheService.get(key);
        final boolean flag = value.equals(tmp);
        if (!flag) {
            // 双重check 检查是不是当前线程
            HttpUtil.out(WebUtil.getCurrentResponse(), Result.error(MsgCode.REPEAT_OPERATION));
            return null;
        }

        try {
            return point.proceed(point.getArgs());
        } catch (Throwable throwable) {
            if (throwable instanceof BusinessException) {
                BusinessException e = (BusinessException) throwable;
                HttpUtil.out(WebUtil.getCurrentResponse(), Result.error(e));
                return null;
            }
            logger.error("doAround --error was :{}", throwable.getMessage());
            HttpUtil.out(WebUtil.getCurrentResponse(), Result.error(MsgCode.FAILED));
            return null;
        } finally {
            if (flag) {
                // 处理完成
                cacheService.remove(key);
            }
        }
    }

    /**
     * 生成缓存key
     *
     * @param point point 切点
     * @return String
     */
    protected abstract String getCacheKey(ProceedingJoinPoint point);

    /**
     * 生成缓存value
     *
     * @param point 切点
     * @return String
     */
    protected abstract String getCacheValue(ProceedingJoinPoint point);
}
