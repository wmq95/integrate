package top.fan2wan.database.redis.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.List;
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

    /**
     * @param script     脚本
     * @param returnType 返回值类型
     * @param keyList    参数列表
     * @throws ClassCastException 会抛出类型转换异常
     */
    public <T> T execute(DefaultRedisScript script, Class<T> returnType, List<String> keyList) {
        Object s = redisTemplate.execute(script, keyList);

        return Objects.isNull(s) ? null : (T) s;
    }

    public <T> T execute(DefaultRedisScript script, RedisSerializer<T> resultSerializer, List<String> keyList) {

        return (T) redisTemplate.execute(script, redisTemplate.getKeySerializer(), resultSerializer, keyList);
    }
}
