package com.zl.gc;

import java.lang.ref.SoftReference;

/**
 * @Author: zl
 * @Date: Created in 2019/11/29
 * <p>
 * 软引用示例
 */
public class SoftRef {
    public static void main(String[] args) {
        Object object = new Object();
        SoftReference<Object> softReference = new SoftReference<>(object);
        //当结束object的引用时，Object对象就成了软引用
        object = null;
        System.gc();
        //重新获得该实例的强引用，如果该实例回收后get为null
        Object o = softReference.get();
        System.out.println(o == null); //内存足够时为false,内存不足时为true
    }
}