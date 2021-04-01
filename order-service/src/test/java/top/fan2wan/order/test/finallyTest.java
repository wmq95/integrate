package top.fan2wan.order.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.fan2wan.order.OrderApplication;
import top.fan2wan.order.service.IUserOrderService;

/**
 * @Author: fanT
 * @Date: 2021/4/1 10:47
 * @Description: test for finally
 */
@SpringBootTest(classes = OrderApplication.class)
@RunWith(SpringRunner.class)
public class finallyTest {

    @Autowired
    private IUserOrderService userOrderService;

    @Test
    public void test() {
        boolean b = true;
        try {
            b = userOrderService.saveWithRollback();
        } finally {
            // 也就是说 finally 就是一定会执行的代码
            // 即使出现异常 也会执行 也会执行 return 语句
            System.out.println("first:" + b);
            b = false;
            //如果有return 。。。这个也会执行
        }
        System.out.println("second:" + b);
    }
}
