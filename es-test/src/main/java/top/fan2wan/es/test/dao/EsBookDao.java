package top.fan2wan.es.test.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import top.fan2wan.es.test.entity.EsBook;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: fanT
 * @Date: 2021/9/6 13:25
 * @Description:
 */
@Repository
public interface EsBookDao extends ElasticsearchRepository<EsBook, Long> {

    List<EsBook> findByCategory(String category);

    List<EsBook> findByPublishTimeBefore(LocalDate date);

    /**
     * 目的想通过id 排序 但是在es 中如果设置实体类的id 为_id 那么排序是按照String 去排序的 因为在es中_id是String格式
     * 如果还想通过实体Id 去排序 怎么办 就是在实体中加一个field fieldType设置为Long 然后用field去做排序
     *
     * @param page
     * @return
     */
    Page<EsBook> findAllByOrderByIdDesc(Pageable page);
}
