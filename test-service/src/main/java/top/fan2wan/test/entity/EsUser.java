package top.fan2wan.test.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author: fanT
 * @Date: 2020/12/16 15:07
 * @Description: entity for user of es
 */
@Data
@Document(indexName = "es", type = "user", shards = 1, replicas = 0)
public class EsUser {

    /**
     * ID
     */
    @Id
    private Long id;


    /**
     * 姓名
     */
    @Field(type = FieldType.Keyword)
    private String name;

    /**
     * 用户名
     */
    @Field(type = FieldType.Keyword)
    private String userName;

    /**
     * 昵称
     */
    @Field(type = FieldType.Text)
    private String nickname;

    /**
     * 密码
     */
    @Field(type = FieldType.Keyword)
    private String password;

    @Field(type = FieldType.Long)
    private Long userId;
}
