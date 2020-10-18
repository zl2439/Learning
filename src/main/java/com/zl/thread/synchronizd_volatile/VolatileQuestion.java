package com.zl.thread.synchronizd_volatile;

/**
 * @Author: zl
 * @Date: Created in 2019/10/21
 * @Description: volatile关键字主要是使变量在多个线程中可见，只可以用于修饰变量
 */
public class VolatileQuestion {
    public static void main(String[] args) {
        PrintString printString = new PrintString();
        printString.setContinuePrint(true);
        System.out.println("begin");
        printString.printMethod();
        System.out.println("停止打印，stopThreadName: " + Thread.currentThread().getName());
        /*
         * 将值赋值为false
         * 打印并不会停止，因为main线程一直在执行while死循环，导致不会执行赋值语句
         * 可以通过多线程的方式解决同步死循环
         * */
        printString.setContinuePrint(false);
    }
}

class PrintString {
    private boolean isContinuePrint;

    public boolean isContinuePrint() {
        return isContinuePrint;
    }

    public void setContinuePrint(boolean continuePrint) {
        isContinuePrint = continuePrint;
    }

    public void printMethod() {
        while (isContinuePrint) {
            System.out.println("run currentThreadName: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
