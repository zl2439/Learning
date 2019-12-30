package com.zl.gc;

import java.lang.ref.WeakReference;

/**
 * @Author: zl
 * @Date: Created in 2019/11/29
 * <p>
 * 弱引用示例
 */
public class WeekRef {
    public static void main(String[] args) {
        //弱引用示例
        WeakReference<String> weakReference = new WeakReference<>(new String("hello"));
        //通知JVM进行垃圾回收
        System.gc();
        String str = weakReference.get();
        System.out.println(str == null); //true
    }
}
