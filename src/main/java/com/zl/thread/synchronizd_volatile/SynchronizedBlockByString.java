package com.zl.thread.synchronizd_volatile;

/**
 * @Author: zl
 * @Date: Created in 2019/10/20
 * @Description:
 *
 * synchronized(string)和String一起使用时，要注意常量池带来的一些特性
 */
public class SynchronizedBlockByString {
    public void method(String str){
        synchronized (str){
            while (true){
                System.out.println("currentThreadName: "+Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("begin");
        ThreadA3 threadA3 = new ThreadA3();
        threadA3.setName("a3");
        ThreadB3 threadB3 = new ThreadB3();
        threadB3.setName("b3");
        //将两个线程都启动，发现只有运行一个线程，另一个线程永远没有运行机会，这是因为两个线程中传入的都是"A"字符串，导致两个线程使用的是同一个锁
        threadA3.start();
        threadB3.start();
    }

}

class ThreadA3 extends Thread{
    @Override
    public void run() {
        super.run();
        new SynchronizedBlockByString().method("A");
    }
}

class ThreadB3 extends Thread{
    @Override
    public void run() {
        super.run();
        new SynchronizedBlockByString().method("A");
    }
}


