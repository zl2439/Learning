package com.zl.thread.base.demo;

/**
 * @Author: zl
 * @Date: Created in 2019/10/14
 * @Description:
 * java.lang.Thread#interrupted()方法演示
 */
public class InterruptedDemo {
    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        System.out.println("当前线程是否停止："+Thread.interrupted()); //结果为true
        /*
        * 第二次执行结果为false，是因为interrupted方法会擦除线程的中断状态，当第一次调用该方法之后，线程的中断状态就被擦除掉
        * 因此第二次调用结果为true
        *
        * interrupted方法有清除状态的功能
        * */
        System.out.println("当前线程是否停止："+Thread.interrupted()); //结果为false
        System.out.println("end");
    }
}
