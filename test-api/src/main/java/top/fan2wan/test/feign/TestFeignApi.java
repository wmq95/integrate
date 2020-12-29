package top.fan2wan.test.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author: fanT
 * @Date: 2020/9/28 16:17
 * @Description: test feignApi
 */
@FeignClient("testService")
@ApiIgnore
public interface TestFeignApi {
    /**
     * test for service
     *
     * @param name name
     * @return name.toLower
     */
    @RequestMapping(value = "testService/index/toLower", method = RequestMethod.GET)
    String toLower(@RequestParam(value = "name") String name);
}
