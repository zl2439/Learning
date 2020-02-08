package com.zl.thread.base.demo;

import java.text.DateFormat;

/**
 * @Author: zl
 * @Date: Created in 2019/10/18
 * @Description: 暂停/恢复线程
 *
 * java.lang.Thread#suspend() 暂停线程
 * java.lang.Thread#resume()  恢复线程
 *
 * 注意，暂停线程容易导致公共对象独占和不同步的问题
 */
public class SuspendThread {
    public static void main(String[] args) {
        System.out.println("begin");
        try {
            MyThread6 myThread6 = new MyThread6();
            myThread6.setName("a");
            myThread6.start();
            Thread.sleep(3000);

            //A段,暂停线程
            myThread6.suspend();
            System.out.println("A = " +
                    DateFormat.getDateTimeInstance().format(System.currentTimeMillis()) +
                    ",i = " + myThread6.getI());
            Thread.sleep(3000);
            System.out.println("A = " +
                    DateFormat.getDateTimeInstance().format(System.currentTimeMillis()) +
                    ",i = " + myThread6.getI());

            //唤醒线程
            myThread6.resume();
            Thread.sleep(3000);

            //B段,暂定线程
            myThread6.suspend();
            System.out.println("B = " +
                    DateFormat.getDateTimeInstance().format(System.currentTimeMillis()) +
                    ",i = " + myThread6.getI());
            Thread.sleep(3000);
            System.out.println("B = " +
                    DateFormat.getDateTimeInstance().format(System.currentTimeMillis()) +
                    ",i = " + myThread6.getI());

            //唤醒线程，处于暂停的线程无法使用interrupt停止
            myThread6.resume();
            //最后停止线程
            myThread6.interrupt();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}

class MyThread6 extends Thread {
    private long i = 0;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            if (isInterrupted()) {
                System.out.println("当前线程以经被终止");
                return;
            }
            i++;
        }
    }
}
