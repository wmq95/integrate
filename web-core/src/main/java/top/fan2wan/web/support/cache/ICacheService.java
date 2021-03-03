package top.fan2wan.web.support.cache;

/**
 * @Author: fanT
 * @Date: 2021/3/2 13:28
 * @Description: service for cache
 */
public interface ICacheService {
    /**
     * 缓存key value 设置过期时间
     *
     * @param key         key
     * @param value       value
     * @param expiredTime 过期时间
     * @return boolean
     */
    boolean put(String key, String value, long expiredTime);

    /**
     * 获取value
     *
     * @param key key
     * @return Object
     */
    Object get(String key);

    /**
     * remove key
     *
     * @param key key
     * @return boolean
     */
    boolean remove(String key);
}
