package com.zl.thread.base.demo;

/**
 * @Author: zl
 * @Date: Created in 2019/10/17
 * @Description:
 * 在沉睡中停止线程，会抛出InterruptedException异常，并且会将线程停止状态值置为false
 */
public class ThreadStopOnSleeping {
    public static void main(String[] args) throws Exception {
        MyThread3 myThread3 = new MyThread3();
        myThread3.start();
        Thread.sleep(1000);
        System.out.println("开始执行interrupt");
        myThread3.interrupt();
        System.out.println("end");
    }
}

class MyThread3 extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("run starting");
        try {
            sleep(200000);
        } catch (InterruptedException e) {
            /*
            * 在睡眠中的停止线程，会产生InterruptedException异常，并且会清除线程停止状态值，使其为false
            * */
            System.out.println("在睡眠中终止，" + this.isInterrupted());
            e.printStackTrace();
        }
        System.out.println("run stopping");
    }
}
