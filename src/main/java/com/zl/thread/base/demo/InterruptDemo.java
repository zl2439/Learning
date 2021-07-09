package com.zl.thread.base.demo;

/**
 * @Author: zl
 * @Date: Created in 2019/10/14
 * @Description: 停止一个线程
 */
public class InterruptDemo {

    /**
     * java.lang.Thread#stop()方法可以停止一个线程，但是最好不要用，因为他是不安全的，并且已经是废弃的
     * java.lang.Thread#interrupt()方法并不会终止一个线程
     *
     *
     * java.lang.Thread#interrupted() 静态方法。测试 当前 线程是否已经中断，当前线程是指运行this.interrupted方法的线程
     * java.lang.Thread#isInterrupted() 非静态方法。测试线程是否已经停止中断
     */
    public static void main(String[] args) {
        try {
            Thread thread = new Thread();

            Mythread mythread = new Mythread();
            mythread.start();

            Thread.sleep(1000);
            /*
            * 虽然在mythread实例上执行interrupt方法停止线程，该方法并不会直接终止一个线程
            * 该方法仅仅是在当前线程中打了一个停止标记，并不会真正的停止线程
            * */
            System.out.println("执行interrupt");
            mythread.interrupt();
            /*
            * 两次结果都为false，因为虽然是是用mythread在调用，
            * 但是在该方法是在main方法中执行的，因此检测是main线程（运行该方法的线程）是否中断，
            * 执行此处时，main线程是没有中断的
            * */
            System.out.println("当前线程是否中断："+mythread.interrupted());
            System.out.println("当前线程是否中断："+mythread.interrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}

class Mythread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300000; i++) {
            System.out.println("i = " + i);
        }
    }
}
