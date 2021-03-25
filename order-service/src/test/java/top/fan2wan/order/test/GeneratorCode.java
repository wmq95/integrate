package top.fan2wan.order.test;

import com.baomidou.mybatisplus.generator.config.PackageConfig;
import top.fan2wan.mysql.util.GeneratorUtil;

/**
 * @Author: fanT
 * @Date: 2021/3/25 14:12
 * @Description: GeneratorCode
 */
public class GeneratorCode {

    public static void main(String[] args) {
        GeneratorUtil generatorUtil = new GeneratorUtil();
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("order");
        pc.setParent("top.fan2wan");
        generatorUtil.generator("jdbc:mysql://www.fan2wan.top:7777/order?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Shanghai",
                "root","123456","order-service",pc,new String[]{"user_order"});
    }
}
