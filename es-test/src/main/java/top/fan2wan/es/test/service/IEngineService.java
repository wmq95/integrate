package top.fan2wan.es.test.service;

import top.fan2wan.es.test.entity.EsPerson;
import top.fan2wan.es.test.param.SavePersonParam;
import top.fan2wan.es.test.param.SearchPersonParam;

import java.util.List;

/**
 * @Author: fanT
 * @Date: 2021/9/7 14:12
 * @Description: 搜索引擎service抽象
 */
public interface IEngineService {

    boolean save(EsPerson person);

    boolean delete(EsPerson person);

    List<EsPerson> search(SearchPersonParam param);

    /**
     * @param param SavePersonParam
     * @return EsPerson
     */
    default EsPerson transform(SavePersonParam param) {

        return null;
    }
}
