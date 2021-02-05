package top.fan2wan.testService;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: fanT
 * @Date: 2021/2/5 11:18
 * @Description: test for fairAndUnfair
 */
public class FairAndUnFair {

    private static final int COUNT = 100;

    private static int start = 1;

    static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        Runnable task = () -> {
            for (; ; ) {
                lock.lock();
                try {
                    if (start <= COUNT) {
                        System.out.println(Thread.currentThread().getName() + "=> " + start++);
                    } else {
                        System.exit(0);
                    }
                } finally {
                    lock.unlock();
                }
            }
        };
        new Thread(task).start();
        new Thread(task).start();
    }

}
