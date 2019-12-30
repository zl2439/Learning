package com.zl.gc;

/**
 * @Author: zl
 * @Date: Created in 2019/12/15
 *
 * GC日志分析
 */
public class GCDemo {
    private byte[] buf = new byte[1024 * 1024];

    public static void main(String[] args) {
        GCDemo gcDemo = new GCDemo();
        System.gc();
    }
}
