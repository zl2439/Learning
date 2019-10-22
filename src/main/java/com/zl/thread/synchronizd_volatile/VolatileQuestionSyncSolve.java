package com.zl.thread.synchronizd_volatile;

/**
 * @Author: zl
 * @Date: Created in 2019/10/21
 * @Description: 解决同步死循环，通过将同步变成异步的方式解决
 * 使用异步的方式可以解决同步造成的死循环，在一般情况下下面的代码不会问题，在主线程中可以使PrintStringSyncSolve停下来
 * <p>
 * 但是当JVM设置成server模式时，再次运行就会发现，在主线程中将isContinuePrint设置成false，
 * 但是PrintStringSyncSolve线程依旧不会停下来
 * 这是因为在-server模式下，private boolean isContinuePrint会存在于公共堆栈和线程的私有栈中
 * 为了运行效率，线程一直在私有线程栈中取值为true
 * printString.setContinuePrint(false)改的却是公共堆栈中的值，因此线程停不下来
 * <p>
 * 解决办法是：使用volatile声明isContinuePrint变量，当线程访问值时强制从公共栈中取值
 */
public class VolatileQuestionSyncSolve {
    public static void main(String[] args) throws Exception {
        PrintStringSyncSolve printString = new PrintStringSyncSolve();
        printString.setContinuePrint(true);
        System.out.println("begin");
        new Thread(printString).start();
        Thread.sleep(3000);

        System.out.println("停止打印，stopThreadName: " + Thread.currentThread().getName());
        printString.setContinuePrint(false);
    }
}

class PrintStringSyncSolve implements Runnable {
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

    @Override
    public void run() {
        printMethod();
    }
}
