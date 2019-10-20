package com.zl.thread.synchronizd_volatile;

/**
 * @Author: zl
 * @Date: Created in 2019/10/20
 * @Description: 当发生的异常的情况下，会释放当前持有的锁
 */
public class LockReleaseOnException {

    public static void main(String[] args) {
        System.out.println("begin");
        LockReleaseOnException lockReleaseOnException = new LockReleaseOnException();
        ThreadA1 threadA1 = new ThreadA1(lockReleaseOnException);
        threadA1.setName("a");
        ThreadB1 threadB1 = new ThreadB1(lockReleaseOnException);
        threadB1.setName("b");

        //启动线程a，正常情况下线程a会一直持有锁
        threadA1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB1.start();
    }

    synchronized public void method() {
        if ("a".equals(Thread.currentThread().getName())) {
            while (true) {
                System.out.println("ThreadName is: " + Thread.currentThread().getName()
                        + ", runTime is:" + System.currentTimeMillis());
                //线程A运行在此处会发生异常
                int a = 5/0;
            }
        } else {
            System.out.println("ThreadName is: " + Thread.currentThread().getName()
                    + ", runTime is:" + System.currentTimeMillis());
        }
    }
}

class ThreadA1 extends Thread{
    private LockReleaseOnException lockReleaseOnException;

    public ThreadA1(LockReleaseOnException lockReleaseOnException) {
        this.lockReleaseOnException = lockReleaseOnException;
    }

    @Override
    public void run() {
        super.run();
        lockReleaseOnException.method();
    }
}

class ThreadB1 extends Thread{
    private LockReleaseOnException lockReleaseOnException;

    public ThreadB1(LockReleaseOnException lockReleaseOnException) {
        this.lockReleaseOnException = lockReleaseOnException;
    }

    @Override
    public void run() {
        super.run();
        lockReleaseOnException.method();
    }
}
