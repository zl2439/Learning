package com.zl.thread.communication;

/**
 * @Author: zl
 * @Date: Created in 2019/10/25
 * <p>
 * 当线程处于wait状态时进行interrupt，会产生异常java.lang.InterruptedException
 * 并且释放当前对象锁
 */
public class WaitAndInterrupt {
    public static void main(String[] args) {
        WaitAndInterrupt waitAndInterrupt = new WaitAndInterrupt();
        Thread1 thread1 = new Thread1(waitAndInterrupt);
        thread1.start();
        thread1.interrupt();
    }

    synchronized public void method() throws Exception {
        System.out.println("begin wait……");
        this.wait();
        System.out.println("end wait……");
    }
}

class Thread1 extends Thread {

    private WaitAndInterrupt waitAndInterrupt;

    public Thread1(WaitAndInterrupt waitAndInterrupt) {
        this.waitAndInterrupt = waitAndInterrupt;
    }

    @Override
    public void run() {
        super.run();
        try {
            waitAndInterrupt.method();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
