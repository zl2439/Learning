package com.zl.thread.synchronizd_volatile;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: zl
 * @Date: Created in 2019/10/23
 * @Description: 使用原子类虽然可以保证最终结果是正确的，但是在有逻辑关系的业务中中间结果是随机的
 */
public class AtomicQuestion {
    public static void main(String[] args) throws Exception {
        System.out.println("begin");
        Service service = new Service();
        Mythread2[] mythread2s = new Mythread2[5];
        for (int i = 0; i < 5; i++) {
            mythread2s[i] = new Mythread2(service);
        }
        for (int i = 0; i < 5; i++) {
            mythread2s[i].start();
        }
        Thread.sleep(3000);
        /*
         * 最后的结果为505，但是中间的过程可能并没有按照线程启动的顺序来
         * 这是因为AtomicLong.addAndGet方法是原子性操作，但是方法和方法之间的调用并不是原子性操作
         * */
        System.out.println("result=" + service.getAtomicLong().get());
    }
}

class Service {
    private AtomicLong atomicLong = new AtomicLong();

    public void addCount() {
        System.out.println("currentThreadName: " + Thread.currentThread().getName() +
                "加100后的结果是：" + atomicLong.addAndGet(100));
        atomicLong.addAndGet(1);
    }

    public AtomicLong getAtomicLong() {
        return atomicLong;
    }

    public void setAtomicLong(AtomicLong atomicLong) {
        this.atomicLong = atomicLong;
    }
}

class Mythread2 extends Thread {
    private Service service;

    public Mythread2(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.addCount();
    }
}
