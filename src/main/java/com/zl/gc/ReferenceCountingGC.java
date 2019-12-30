package com.zl.gc;

/**
 * @Author: zl
 * @Date: Created in 2019/11/27
 * <p>
 * 引用计数分析
 * -verbose:gc
 * -XX:+PrintGC    同-verbose:gc等价
 * -XX:+PrintGCDetails
 * -XX:+UseSerialGC
 */
public class ReferenceCountingGC {
    private static final int MB = 1024 * 1024;
    public Object instance;

    private byte[] size = new byte[MB];

    public static void main(String[] args) {
        //强引用
        ReferenceCountingGC o1 = new ReferenceCountingGC();
        ReferenceCountingGC o2 = new ReferenceCountingGC();
        //互相引用（循环引用）
        o1.instance = o2;
        o2.instance = o1;

        o1 = null;
        o2 = null;
        //按理说引用计数不为0，不会进行回收
        System.gc();
    }
}
