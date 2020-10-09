package top.fan2wan.web.config;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author: fanT
 * @Date: 2020/10/9 15:10
 * @Description: abstract config for dozer
 */
@Configuration
public abstract class AbstractDozerConfig {

    /**
     * 默认dozer 配置文件路径
     * 在common-util 对应位置下已有空文件
     * 在各自resource目录下新建该文件 可以达到覆盖效果
     */
    private static final String DOZER_CONFIG_DEFAULT_PATH = "dozer/dozer.xml";

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        DozerBeanMapper dozerBean = new DozerBeanMapper();
        List<String> files = Lists.newArrayList(DOZER_CONFIG_DEFAULT_PATH);
        dozerBean.setMappingFiles(files);
        return dozerBean;
    }
}
