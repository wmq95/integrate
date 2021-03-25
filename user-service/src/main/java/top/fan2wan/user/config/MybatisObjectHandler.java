package top.fan2wan.user.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @Author: fanT
 * @Date: 2020/9/30 14:18
 * @Description: MybatisObjectHandler
 */
@Component
@MapperScan("top.fan2wan.user.mapper*")
public class MybatisObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
//        LocalDateTime now = LocalDateTime.now();
        //新增时填充的字段
//        setFieldValByName("gmtCreate", now, metaObject);
//        setFieldValByName("gmtModified", now, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        setFieldValByName("gmtModified", LocalDateTime.now(), metaObject);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        return hikariDataSource;
    }

  /*  @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);
        return factory;
    }

    @Bean
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSourceProxy) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSourceProxy);
        return transactionManager;
    }*/
}
