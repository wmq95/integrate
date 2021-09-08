package top.fan2wan.es.test.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import top.fan2wan.es.test.entity.EsPerson;

import java.util.List;

/**
 * @Author: fanT
 * @Date: 2021/9/7 9:27
 * @Description:
 */
@Repository
public interface EsPersonDao extends ElasticsearchRepository<EsPerson, Long> {

    List<EsPerson> findAllByAddressCity(String name);

    List<EsPerson> findAllByAddressCityAndAddressCountry(String city, String country);

//    List<EsPerson>
}
