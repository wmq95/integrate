package top.fan2wan.order.test;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: fanT
 * @Date: 2021/4/12 9:28
 * @Description: test for oom
 * 当堆栈oom 的时候 测试其他线程能否正常运作
 */
public class OOMTest {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            List<byte[]> byteList = new LinkedList<>();
            while (true) {
                byte[] bytes = new byte[1024 * 1024 * 5];
                System.out.println("new bytes for 5M");
                try {
                    Thread.sleep(1000 * 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                byteList.add(bytes);
            }
        });


        Thread work = new Thread(() -> {
            while (true) {

                System.out.println("i am working .......");
                try {
                    Thread.sleep(1000 * 3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        work.start();
    }
}
