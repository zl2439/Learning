package com.zl.thread.base.demo;

/**
 * @Author: zl
 * @Date: Created in 2019/10/18
 * @Description:
 * java.lang.Thread#yield() yield会使线程释放CPU资源
 * 但是放弃时间不确定，有可能放弃了之后，又会再次获得
 */
public class yieldDemo {
    public static void main(String[] args) {
        System.out.println("begin");
        new MyThread7().start();
        System.out.println("end");
    }
}

class MyThread7 extends Thread {
    private long count;

    @Override
    public void run() {
        super.run();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000000; i++) {
            //使线程放弃CPU
            Thread.yield();
            count = count + (i++);
        }
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end - start) + "ms");
    }
}

