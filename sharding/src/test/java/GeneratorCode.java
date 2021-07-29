import com.baomidou.mybatisplus.generator.config.PackageConfig;
import top.fan2wan.mysql.util.GeneratorUtil;

/**
 * @Author: fanT
 * @Date: 2021/7/29 13:51
 * @Description:
 */
public class GeneratorCode {

    public static void main(String[] args) {
        GeneratorUtil generatorUtil = new GeneratorUtil();
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("sharding");
        pc.setParent("top.fan2wan");
        generatorUtil.generator("jdbc:mysql://mysql:7777/test?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai",
                "fant","123456","sharding",pc,new String[]{"user"});
    }
}
