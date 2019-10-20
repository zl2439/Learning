package com.zl.thread.synchronizd_volatile;

/**
 * @Author: zl
 * @Date: Created in 2019/10/20
 * @Description:
 *
 * 证明synchronized锁住的是对象而不是函数本身
 */
public class SynchronizedLockObject {

    public synchronized void methodA() {
        System.out.println("begin methodA，ThreadName:" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

    public synchronized void methodB() {
        System.out.println("begin methodB，ThreadName:" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        System.out.println("begin");
        SynchronizedLockObject synchronizedLockObject = new SynchronizedLockObject();
        ThreadA threadA = new ThreadA(synchronizedLockObject);
        threadA.setName("threadA");
        ThreadB threadB = new ThreadB(synchronizedLockObject);
        threadB.setName("threadB");
        /*
         * 先启动threadA，threadA先持有对象的lock锁
         * 在启动threadB，同样想要访问synchronized修饰的方法，就需要等待threadA方法释放对象的lock锁
         * 证明synchronized锁住的是对象，而不是对应函数
         * */
        threadA.start();
        threadB.start();
    }
}

class ThreadA extends Thread {
    private SynchronizedLockObject s;

    public ThreadA(SynchronizedLockObject s) {
        this.s = s;
    }

    @Override
    public void run() {
        super.run();
        s.methodA();
    }
}

class ThreadB extends Thread {
    private SynchronizedLockObject s;

    public ThreadB(SynchronizedLockObject s) {
        this.s = s;
    }

    @Override
    public void run() {
        super.run();
        s.methodB();
    }
}

