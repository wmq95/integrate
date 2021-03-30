package top.fan2wan.order.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.fan2wan.api.dto.Result;
import top.fan2wan.order.feign.IUserOrderFeign;
import top.fan2wan.order.service.IUserOrderService;

/**
 * <p>
 * order 前端控制器
 * </p>
 *
 * @author fanT
 * @since 2021-03-25
 */
@RestController
@AllArgsConstructor
public class UserOrderController implements IUserOrderFeign{

    final IUserOrderService userOrderService;
    @Override
    public Result<Boolean> saveTest() {
        return Result.success(userOrderService.saveTest());
    }

    @Override
    public Result<Boolean> sendMqMsg() {
        return Result.success(userOrderService.sendMqMsg());
    }
}
