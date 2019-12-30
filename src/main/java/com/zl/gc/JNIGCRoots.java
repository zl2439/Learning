package com.zl.gc;

/**
 * @Author: zl
 * @Date: Created in 2019/11/29
 * <p>
 * 本地方法栈中JNI引用的对象说明
 */
public class JNIGCRoots {

    //Person引用的对象可以作为GC Roots
    private native void demo(Person person);

    void test() {
        Person person = new Person();
        demo(person);
    }
}

class Person {
    private String name;
}
