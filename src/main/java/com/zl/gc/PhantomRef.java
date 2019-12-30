package com.zl.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @Author: zl
 * @Date: Created in 2019/11/29
 * <p>
 * 虚引用示例
 */
public class PhantomRef {
    public static void main(String[] args) {
        //虚引用示例
        PhantomReference<String> phantomReference =
                new PhantomReference<>(new String("hello"), new ReferenceQueue<>());
        System.out.println(phantomReference.get()); //null
    }
}
