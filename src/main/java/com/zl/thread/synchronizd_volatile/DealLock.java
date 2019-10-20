package com.zl.thread.synchronizd_volatile;

/**
 * @Author: zl
 * @Date: Created in 2019/10/20
 * @Description: 死锁
 */
public class DealLock implements Runnable {
    private String userName;

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void run() {
        if ("a".equals(this.userName)) {
            synchronized (lock1) {
                try {
                    System.out.println("userName: " + this.userName);
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("lock1 -> lock2");
                }
            }
        }
        if ("b".equals(this.userName)) {
            synchronized (lock2) {
                try {
                    System.out.println("userName: " + this.userName);
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("lock2 -> lock1");
                }
            }
        }

    }

    public static void main(String[] args) throws Exception{
        DealLock dealLock = new DealLock();
        dealLock.setUserName("a");
        Thread threada = new Thread(dealLock);
        threada.start();
        Thread.sleep(100);
        dealLock.setUserName("b");
        Thread threadb = new Thread(dealLock);
        threadb.start();
    }
}

