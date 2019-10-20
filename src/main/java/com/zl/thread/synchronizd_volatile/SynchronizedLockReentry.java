package com.zl.thread.synchronizd_volatile;

/**
 * @Author: zl
 * @Date: Created in 2019/10/20
 * @Description:
 *
 * synchronized锁重入
 * 在一个synchronized方法/块中调用本类中其他synchronized方法/时，是永远可以得到锁的
 * “可重入锁”的概念是，自己可以再次获得自己的内部锁，假如一个线程获得了某对象的锁，当其想再次获取该对象的锁时是可以获取的
 * 如果不可锁重入的话，就会造成死锁
 *
 * 可重入锁也支持在子父类关系中，子类完全可以通过可重入锁调用父类的同步方法
 */
public class SynchronizedLockReentry {
    public static void main(String[] args) {
        System.out.println("begin");
        Thread1 thread1 = new Thread1();
        thread1.start();
        System.out.println("edn");
    }

    public synchronized void service1(){
        System.out.println("service1……");
        serivie2();
    }
    public synchronized void serivie2(){
        System.out.println("service2……");
        service3();
    }
    public synchronized void service3(){
        System.out.println("service3……");
    }
}

class Thread1 extends Thread{

    @Override
    public void run() {
        super.run();
        new SynchronizedLockReentry().service1();
    }
}

