package com.zl.thread.base.demo;

/**
 * @Author: zl
 * @Date: Created in 2019/10/14
 * @Description: 停止一个线程
 *
 * 通过在run中判断并加上throw异常的方法停止线程
 */
public class ThreadStopByException {

    /**
     * java.lang.Thread#stop()方法可以停止一个线程，但是最好不要用，因为他是不安全的，并且已经是废弃的
     * java.lang.Thread#interrupt()方法并不会终止一个线程
     *
     *
     * java.lang.Thread#interrupted() 测试 当前 线程是否已经中断，当前线程是指运行this.interrupted方法的线程
     * java.lang.Thread#isInterrupted() 测试线程是否已经停止中断
     */
    public static void main(String[] args) {
        try {
            Mythread2 mythread = new Mythread2();
            mythread.start();
            Thread.sleep(2000);
            System.out.println("开始执行interrupt");
            mythread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}

class Mythread2 extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 300000; i++) {
                if(this.interrupted()){
                    System.out.println("我已经是停止状态了，我要退出");
                    //手动抛出线程终端异常
                    throw new InterruptedException();
                }
                System.out.println("i = " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
