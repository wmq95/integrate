package top.fan2wan.test.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: fanT
 * @Date: 2020/9/30 14:18
 * @Description: MybatisObjectHandler
 */
@Component
public class MybatisObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        //新增时填充的字段
        setFieldValByName("gmtCreate", now, metaObject);
        setFieldValByName("gmtModified", now, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("gmtModified", LocalDateTime.now(), metaObject);
    }
}
