package com.zl.thread.base.demo;

/**
 * @Author: zl
 * @Date: Created in 2019/10/14
 * @Description: 停止一个线程
 *
 * isInterrupted方法演示
 */
public class IsInterruptedDemo {

    /**
     * java.lang.Thread#interrupted() 测试 当前 线程是否已经中断，当前线程是指运行this.interrupted方法的线程
     * java.lang.Thread#isInterrupted() 测试Thread对象是否已经是停止状态
     */
    public static void main(String[] args) {
        try {
            Mythread1 mythread = new Mythread1();
            mythread.start();
            Thread.sleep(1000);
            mythread.interrupt();
            /*
            * isInterrupted判段的是Thread对象是否已经是终止状态，
            * 并且不会清除状态标志
            * */
            System.out.println("线程是否中断："+mythread.isInterrupted()); //true
            System.out.println("线程是否中断："+mythread.isInterrupted()); //true
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}

class Mythread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 200000; i++) {
            System.out.println("i = " + i);
        }
    }
}
