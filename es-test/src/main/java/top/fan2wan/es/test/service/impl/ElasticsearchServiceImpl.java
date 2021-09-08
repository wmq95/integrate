package top.fan2wan.es.test.service.impl;

import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;
import top.fan2wan.es.test.dao.EsPersonDao;
import top.fan2wan.es.test.entity.EsPerson;
import top.fan2wan.es.test.param.SearchPersonParam;
import top.fan2wan.es.test.service.IEngineService;

import java.util.List;

/**
 * @Author: fanT
 * @Date: 2021/9/8 8:34
 * @Description: impl for elasticsearch
 */
@Service
public class ElasticsearchServiceImpl implements IEngineService {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private final EsPersonDao esPersonDao;

    public ElasticsearchServiceImpl(ElasticsearchRestTemplate elasticsearchRestTemplate, EsPersonDao esPersonDao) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
        this.esPersonDao = esPersonDao;
    }

    @Override
    public boolean save(EsPerson person) {
        esPersonDao.save(person);
        return true;
    }

    @Override
    public boolean delete(EsPerson person) {

        esPersonDao.deleteById(person.getId());
        return true;
    }

    @Override
    public List<EsPerson> search(SearchPersonParam param) {

        // 使用elasticsearchRestTemplate 做查询
        return null;
    }
}
