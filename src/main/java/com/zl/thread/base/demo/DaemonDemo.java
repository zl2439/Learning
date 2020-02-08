package com.zl.thread.base.demo;

/**
 * @Author: zl
 * @Date: Created in 2019/10/18
 * @Description: 守护线程
 * Java中的线程分为两种: 用户线程和守护线程
 * 任何守护线程都是是整个JVM中所有非守护线程的"保姆"
 * 只要当前JVM进程中存在任何一个非守护进程没有结束时，守护进程就在工作
 * 只有当最后一个非守护进程结束时，守护进程才会随着JVM一起结束工作
 */
public class DaemonDemo {
    public static void main(String[] args) throws Exception{
        System.out.println("begin");
        MyThread8 myThread8 = new MyThread8();
        //设置为守护进程
        myThread8.setDaemon(true);
        myThread8.start();
        Thread.sleep(1000);
        System.out.println("main结束之后，mythread线程也不再打印");
        System.out.println("end");
    }
}

class MyThread8 extends Thread {
    private int i = 0;

    @Override
    public void run() {
        super.run();
        while (true) {
            System.out.println("i = " + ++i);
        }
    }
}
