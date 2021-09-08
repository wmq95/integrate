package top.fan2wan.es.test;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.test.context.junit4.SpringRunner;
import top.fan2wan.es.test.dao.EsBookDao;
import top.fan2wan.es.test.dao.EsPersonDao;
import top.fan2wan.es.test.entity.EsBook;
import top.fan2wan.es.test.entity.EsPerson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: fanT
 * @Date: 2021/9/6 13:19
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataEsTest {

    @Autowired
    EsBookDao esBookDao;
    @Autowired
    EsPersonDao esPersonDao;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void saveTest() {
        EsBook esBook = new EsBook();
        esBook.setId(1100011L);
        esBook.setCategory("数学");
        esBook.setName("一年级数学");
        esBook.setPublisher("中国出版社");
        esBook.setPublishTime(LocalDate.now());
        EsBook save = esBookDao.save(esBook);
        System.out.println("保存结果:" + save);
    }

    @Test
    public void insertBatchTest() {
        LocalDate now = LocalDate.now();
        List<EsBook> list = new ArrayList<>(10);
        for (int i = 0; i < 8; i++) {
            EsBook esBook = new EsBook();
            esBook.setId((2001L + i));
            esBook.setCategory(i + "年级");
            esBook.setName(i + "年级教材");
            esBook.setPublisher("中国 " + i + "出版社");
            esBook.setPublishTime(now.minusMonths(i));
            esBook.setLevel("L" + i);
            list.add(esBook);
        }
        Iterable<EsBook> esBooks = esBookDao.saveAll(list);
        esBooks.forEach(System.out::println);
        System.out.println("save success");
    }

    @Test
    public void queryTest() {
        /*List<EsBook> res = esBookDao.findByCategory("语文");
        System.out.println(res);

        System.out.println("===========");

        res = esBookDao.findByPublishTimeBefore(LocalDate.now());
        System.out.println(res);*/
        Pageable page = PageRequest.of(0, 15);
        Page<EsBook> result = esBookDao.findAllByOrderByIdDesc(page);
        System.out.println(result);
        System.out.println(result.getTotalElements());
        System.out.println(result.getTotalPages());
        System.out.println(result.getSize());
        result.get().forEach(System.out::println);
    }


    @Test
    public void personTest() {
        /*List<Address> list = new ArrayList<>(2);
        Address address = new Address();
        address.setCity("wuxi");
        address.setProvince("wuxi");
        address.setCountry("wuxi");
        list.add(address);

        Address address2 = new Address();
        address2.setCity("suzhou");
        address2.setProvince("suzhou");
        address2.setCountry("suzhou");
        list.add(address2);

        EsPerson person = new EsPerson();
        person.setAddress(list);
        person.setAge(30);
        person.setId(22L);
        person.setName("wuxi");

//        esPersonDao.save(person);
//        System.out.println("save person success.....");
        elasticsearchRestTemplate.save(person);
        System.out.println(" esTemplate save success.....");*/


//        System.out.println("find all....");
//        esPersonDao.findAll().forEach(System.out::println);
//        esPersonDao.findAllByAddressCity("nanjing").forEach(System.out::println);

//        esPersonDao.findAllByAddressCityAndAddressCountry("wuxi","suzhou")
//                .forEach(System.out::println);

//        elasticsearchRestTemplate.delete("22", IndexCoordinates.of("person"));

        // 嵌套查询 使用的是elasticsearchRestTemplate+ QueryBuilder
       /* BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termQuery("address.city", "wuxi"));
        boolQueryBuilder.must(QueryBuilders.termQuery("address.country", "china"));
        QueryBuilders.nestedQuery("address", boolQueryBuilder, ScoreMode.Total);

        SearchHits<EsPerson> result = elasticsearchRestTemplate.search(new NativeSearchQuery(boolQueryBuilder), EsPerson.class, IndexCoordinates.of("person"));

        result.getSearchHits().forEach(System.out::println);*/



        /*Map<String, Object> param = new HashMap<>();
        param.put("name", "update");
        elasticsearchRestTemplate.update(UpdateQuery.builder("22")
                .withDocument(Document.from(param))
                .build(), IndexCoordinates.of("person"));*/

        System.out.println("===========");
        EsPerson person = elasticsearchRestTemplate.get("22", EsPerson.class);
        System.out.println(person);
    }
}
