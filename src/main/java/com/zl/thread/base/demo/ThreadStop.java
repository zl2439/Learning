package com.zl.thread.base.demo;

/**
 * @Author: zl
 * @Date: Created in 2019/10/17
 * @Description: 使用stop方法暴力停止线程
 * 调用stop方法时会抛出ThreadDeath异常，通常情情况下该异常不需要显示捕捉
 */
public class ThreadStop {
    public static void main(String[] args) {
        MyThread4 myThread4 = new MyThread4();
        myThread4.start();
    }
}

class MyThread4 extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            this.stop();
        } catch (ThreadDeath e) {
            e.printStackTrace();
        }
    }
}