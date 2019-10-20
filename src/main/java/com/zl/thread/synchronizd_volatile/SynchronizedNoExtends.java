package com.zl.thread.synchronizd_volatile;

/**
 * @Author: zl
 * @Date: Created in 2019/10/20
 * @Description: synchronized不可以被继承
 */
public class SynchronizedNoExtends extends Fu {

    public static void main(String[] args) {
        System.out.println("begin");
        SynchronizedNoExtends synchronizedNoExtends = new SynchronizedNoExtends();
        ThreadA2 threadA2 = new ThreadA2(synchronizedNoExtends);
        threadA2.setName("a");
        ThreadB2 threadB2 = new ThreadB2(synchronizedNoExtends);
        threadB2.setName("b");
        threadA2.start();
        threadB2.start();

    }

    /*
     * 当子类重写父类的同步方法时，子类并不会继承父类的synchronized属性
     * */
    public void method() {
        System.out.println("son method begin, threadName is: " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("son method stop, threadName is: " + Thread.currentThread().getName());
        super.method();
    }

}

class Fu {
    synchronized public void method() {
        System.out.println("fu method begin, threadName is: " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fu method stop, threadName is: " + Thread.currentThread().getName());
    }
}

class ThreadA2 extends Thread {
    public SynchronizedNoExtends synchronizedNoExtends;

    public ThreadA2(SynchronizedNoExtends synchronizedNoExtends) {
        this.synchronizedNoExtends = synchronizedNoExtends;
    }

    @Override
    public void run() {
        super.run();
        synchronizedNoExtends.method();
    }
}

class ThreadB2 extends Thread {
    public SynchronizedNoExtends synchronizedNoExtends;

    public ThreadB2(SynchronizedNoExtends synchronizedNoExtends) {
        this.synchronizedNoExtends = synchronizedNoExtends;
    }

    @Override
    public void run() {
        super.run();
        synchronizedNoExtends.method();
    }
}
