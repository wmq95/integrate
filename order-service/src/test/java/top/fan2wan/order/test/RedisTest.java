package top.fan2wan.order.test;

import io.lettuce.core.RedisClient;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisScriptingCommands;

/**
 * @Author: fanT
 * @Date: 2021/8/11 15:01
 * @Description:
 */
public class RedisTest {

    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://10.15.9.110:6379");
        String script = "--根据给定的key 扣减商品数量\n" +
                "if (tonumber(redis.call('get', KEYS[1])) > 0)\n" +
                "\tthen\n" +
                "\t\tredis.call('incrby', KEYS[1], -1)\n" +
                "\t\treturn 1\n" +
                "\telse\n" +
                "\t\treturn 0\n" +
                "end\n";
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisScriptingCommands commands = connection.sync();
        commands.scriptLoad(script);
        String[] keys = new String[]{"num"};
        Object result = commands.eval(script, ScriptOutputType.INTEGER, keys);
        System.out.println(result);
    }
}
