package com.zl.thread.communication;

/**
 * @Author: zl
 * @Date: Created in 2019/10/25
 * <p>
 * 执行wait方法之后，当前线程立即释放锁，在从wait()返回前，线程与其他线程竞争重新获得锁
 * 执行notify方法之后，当前线程不会立马释放该对象锁，呈wait状态的线程也不会立马获得该对象的锁，
 * 需要等执行notify()的线程执行完毕，也就是执行完synchronized代码块或synchronized方法之后，当前线程才会释放锁
 */
public class WaitAndNotifyReleaseLock {

    public static void main(String[] args) {
        System.out.println("begin");
        WaitAndNotifyReleaseLock waitAndNotifyReleaseLock = new WaitAndNotifyReleaseLock();
        ThreadA threadA = new ThreadA(waitAndNotifyReleaseLock);
        threadA.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadB threadB = new ThreadB(waitAndNotifyReleaseLock);
        threadB.start();
    }

    synchronized public void waitMethod() {
        System.out.println("before wait…… ThreadName：" + Thread.currentThread().getName());
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end wait…… ThreadName：" + Thread.currentThread().getName());
    }

    public void notifyMethod() {
        try {
            System.out.println("before synchronized……");
            synchronized (this) {
                System.out.println("before notify…… ThreadName: " + Thread.currentThread().getName());
                this.notify();
                System.out.println("sleep……");
                Thread.sleep(3000);
                for (int i = 0; i < 5; i++) {
                    System.out.println("i = " + i);
                }
                System.out.println("end notify…… ThreadName: " + Thread.currentThread().getName());
            }
            Thread.sleep(3000);
            System.out.println("end synchronized……");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class ThreadA extends Thread {
    private WaitAndNotifyReleaseLock waitAndNotifyReleaseLock;

    public ThreadA(WaitAndNotifyReleaseLock waitAndNotifyReleaseLock) {
        this.waitAndNotifyReleaseLock = waitAndNotifyReleaseLock;
    }

    @Override
    public void run() {
        super.run();
        waitAndNotifyReleaseLock.waitMethod();
    }
}

class ThreadB extends Thread {
    private WaitAndNotifyReleaseLock waitAndNotifyReleaseLock;

    public ThreadB(WaitAndNotifyReleaseLock waitAndNotifyReleaseLock) {
        this.waitAndNotifyReleaseLock = waitAndNotifyReleaseLock;
    }

    @Override
    public void run() {
        super.run();
        waitAndNotifyReleaseLock.notifyMethod();
    }
}
