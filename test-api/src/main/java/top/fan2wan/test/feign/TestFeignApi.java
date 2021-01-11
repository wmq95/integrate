package top.fan2wan.test.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

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

    /**
     * test for feign
     *
     * @param name name
     * @return name.toUpper
     */
    @RequestMapping(value = "testService/index/testForName", method = RequestMethod.GET)
    String testForName(@RequestParam(value = "name") String name);

    /**
     * test for future
     *
     * @param name name
     * @return future<String>
     */
    @RequestMapping(value = "testService/index/testForFuture", method = RequestMethod.GET)
    FutureTask<String> testForFuture(@RequestParam(value = "name") String name);

    /**
     * testForCallable
     *
     * @param name name
     * @return callable
     */
    @RequestMapping(value = "testService/index/testForCallable", method = RequestMethod.GET)
    Callable<String> testForCallable(@RequestParam(value = "name") String name);

    /**
     * testForRequestHolder
     *
     * @param name name
     * @return String
     */
    @RequestMapping(value = "testService/index/testForRequestHolder", method = RequestMethod.GET)
    String testForRequestHolder(@RequestParam(value = "name") String name);
}
