package top.fan2wan.database.redis.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;
import java.util.Optional;

/**
 * @Author: fanT
 * @Date: 2021/4/16 16:35
 * @Description: util for redis
 */
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 加法
     *
     * @param key key
     * @param num number
     * @return value
     */
    public int increase(String key, int num) {

        return redisTemplate.opsForValue().increment(key, num).intValue();
    }


    public Optional<Integer> getAsInteger(String key) {

        Object obj = get(key);
        if (Objects.isNull(obj)) {
            return Optional.of(null);
        }
        return Optional.of(Integer.parseInt(obj.toString()));
    }

    public Object get(String key) {

        return redisTemplate.opsForValue().get(key);
    }
}
