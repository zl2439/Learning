package com.zl.thread.synchronizd_volatile;

/**
 * @Author: zl
 * @Date: Created in 2019/10/22
 * @Description: volatile使变量在多个线程中可见，但是volatile不具备同步性，也就不具备原子性
 * 对于volatile修饰的变量，JVM只是保证从主内存加载到线程工作内存中的值是最新的，也就是说volatile解决的是变量读时的可见性，
 * 但无法保证原子性，对于多个线程访问同一个实例变量还是需要加锁同步的
 * <p>
 * 以下示例验证了volatile不支持原子性操作，通过运行结果得知，最后结果并不是10000
 */
public class VolatileNotAtomicity {
    public static void main(String[] args) {
        System.out.println("begin");
        MyThread[] myThreads = new MyThread[100];
        for (int i = 0; i < 100; i++) {
            myThreads[i] = new MyThread();
        }
        for (int i = 0; i < 100; i++) {
            myThreads[i].start();
        }
    }
}

class MyThread extends Thread {
    volatile private static int count = 0;

    /**
     * 如果该方法使用synchronized修饰，那么方法整体保证了同步性，count变量也就不再需要使用volatile修饰了
     */
    private static void addCount() {
        for (int i = 0; i < 100; i++) {
            /*
             * 该操作不具备原子性
             * count++，也就是count=count+1操作可以分解为如下三步：
             * 从内存中取值
             * 对值进行操作
             * 将值写入都内存中
             * */
            count++;
        }
        System.out.println("count=" + count);
    }

    @Override
    public void run() {
        super.run();
        addCount();
    }
}
