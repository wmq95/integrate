package top.fan2wan.sharding.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.fan2wan.sharding.service.ITUserOrderService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fanT
 * @since 2021-07-30
 */
@RestController
@RequestMapping("/sharding/t-user-order")
@AllArgsConstructor
public class TUserOrderController {

    private final ITUserOrderService tUserOrderService;

    @RequestMapping("/save")
    public boolean save() {

        return tUserOrderService.saveBySharding();
    }
}
