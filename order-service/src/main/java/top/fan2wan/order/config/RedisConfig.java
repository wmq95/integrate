package top.fan2wan.order.config;

import com.google.common.collect.Maps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import top.fan2wan.database.redis.config.AbstractStringRedisConfig;
import top.fan2wan.order.constant.StringConstant;

import java.util.Map;

/**
 * @Author: fanT
 * @Date: 2021/4/16 16:43
 * @Description: config for redis
 */
@Configuration
public class RedisConfig extends AbstractStringRedisConfig {

    @Bean
    public Map<String, DefaultRedisScript> redisScripts() {
        //redis-lua
        Map<String, DefaultRedisScript> redisScriptMap = Maps.newHashMap();
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis-lua.lua")));
        redisScript.setResultType(Long.class);
        redisScriptMap.put(StringConstant.REDIS_LUA_KEY, redisScript);

        //redis-getPojo
        // 返回String 字符串
        DefaultRedisScript<String> pojoScript = new DefaultRedisScript<>();
        pojoScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis-getPojo.lua")));
        pojoScript.setResultType(String.class);
        redisScriptMap.put(StringConstant.REDIS_LUA_GET_POJO_KEY, pojoScript);
        return redisScriptMap;
    }
}
