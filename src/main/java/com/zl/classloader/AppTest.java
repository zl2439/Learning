package com.zl.classloader;

/**
 * @Author: zl
 * @Date: Created in 2019/11/25
 *
 * 堆、方法区和栈的关系
 */
public class AppTest {
    private String str = "1234";
    public static void main(String[] args) {
        String s = new String("1234");
        /*
         * 局部变量，存储在虚拟机栈的局部变量表中，假设地址为0x10
         * person =》reference
         * */
        Person person;
        /*
         * 在堆上产生一个实例，假设地址时0x21
         * person存储着地址0x21
         * 方法区中存放着Person类的信息，假设地址是0x00001
         * 也就是0x10 => 0x21 => 0x00001
         * */
        person = new Person();
        person.sayHello();
    }
}

class Person {
    public void sayHello() {
        System.out.println("hello");
    }
}
