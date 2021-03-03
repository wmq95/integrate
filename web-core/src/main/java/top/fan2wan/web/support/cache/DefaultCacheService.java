package top.fan2wan.web.support.cache;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @Author: fanT
 * @Date: 2021/3/3 9:48
 * @Description: defaultService for cache
 */
public class DefaultCacheService implements ICacheService {

    private static Map<String, Object> cache = Maps.newConcurrentMap();

    /**
     * 缓存key value 设置过期时间
     *
     * @param key         key
     * @param value       value
     * @param expiredTime 过期时间
     * @return boolean
     */
    @Override
    public boolean put(String key, String value, long expiredTime) {
        if (StrUtil.isBlank(key)) {
            return false;
        }
        cache.put(key, value);
        return true;
    }

    /**
     * 获取value
     *
     * @param key key
     * @return Object
     */
    @Override
    public Object get(String key) {
        return cache.get(key);
    }

    /**
     * remove key
     *
     * @param key key
     * @return boolean
     */
    @Override
    public boolean remove(String key) {
        if (StrUtil.isBlank(key)) {
            return true;
        }
        cache.remove(key);
        return true;
    }
}
