package top.fan2wan.es.test.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

/**
 * @Author: fanT
 * @Date: 2021/9/6 13:26
 * @Description:
 */
@Document(indexName = "book", createIndex = true)
public class EsBook {

    @Id
    @Field(type = FieldType.Long)
    private Long id;

    /***
     * 书名 分词器
     */
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String name;

    /**
     * 出版社
     */
    @Field(type = FieldType.Keyword)
    private String publisher;

    /**
     * 类别
     */
    @Field(type = FieldType.Keyword)
    private String category;

    /**
     * 著作日期
     */
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private LocalDate publishTime;


    //    ======= 业务其他属性
    @Field(type = FieldType.Keyword)
    private String level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDate publishTime) {
        this.publishTime = publishTime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EsBook{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", publisher='").append(publisher).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", publishTime=").append(publishTime);
        sb.append('}');
        return sb.toString();
    }
}
