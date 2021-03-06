package top.fan2wan.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import top.fan2wan.api.dto.Result;

/**
 * @Author: fanT
 * @Date: 2021/3/25 13:59
 * @Description: feign for order
 */
@FeignClient("order")
public interface IUserOrderFeign {

    @RequestMapping(value = "/order/saveTest", method = RequestMethod.POST, consumes = "application/json")
    Result<Boolean> saveTest();

    @RequestMapping(value = "order/placeOrder", method = RequestMethod.GET)
    Result<Boolean> placeOrder(@RequestParam("userId") Long userId);

    @RequestMapping(value = "order/placeOrderWithMq", method = RequestMethod.GET)
    Result<Boolean> placeOrderWithMq(@RequestParam("userId") Long userId);
}
