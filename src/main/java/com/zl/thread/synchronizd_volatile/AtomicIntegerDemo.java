package com.zl.thread.synchronizd_volatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zl
 * @Date: Created in 2019/10/22
 * @Description: 使用原子类实现i++同步操作
 * 原子操作是不可分割的整体，没有其他线程可以中断和检查正在原子操作中的变量
 * 一个原子(atomic)类型就是一个原子操作可用的类型，它可以在没有锁的情况下做到线程安全
 */
public class AtomicIntegerDemo {
    public static void main(String[] args) {
        AddCount addCount = new AddCount();
        System.out.println("begin");
        new Thread(addCount).start();
        new Thread(addCount).start();
        new Thread(addCount).start();
        new Thread(addCount).start();
        new Thread(addCount).start();
    }
}

class AddCount extends Thread {
    /*
    * 使用integer的原子操作可用的类型
    * */
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 10000; i++) {
            System.out.println(atomicInteger.incrementAndGet());
        }
    }
}
