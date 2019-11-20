package com.zl.thread.communication;

/**
 * @Author: zl
 * @Date: Created in 2019/10/25
 * @Description: java.lang.Object#wait()
 * 使用wait方法时，线程必须获得该对象的对象级别锁，即只能在同步方法和同步代码块中调用wait方法
 * 否则就会抛出java.lang.IllegalMonitorStateException异常
 */
public class WaitDemo {

    public static void main(String[] args) {
        System.out.println("begin");
        try {
            new String().wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
