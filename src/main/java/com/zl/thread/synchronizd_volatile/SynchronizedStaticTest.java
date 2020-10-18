package com.zl.thread.synchronizd_volatile;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author: zl
 * @Date: Created in 2020/4/15
 * <p>
 * 静态方法加锁后，验证使用不同类加器的情况下（即不同的Class对象），是否会有线程安全问题
 * 结论：当使用不同的类加载器之后，相同类的class对象不唯一，静态方法加锁会看起来失效了，本质并没有失效，
 * 因为相同的类被不同的类加载器加载之后，这个类在内存中存在两份
 */
public class SynchronizedStaticTest {

    private static int num = 0;
    public static Object object = new Object();

    public synchronized static void test(String flag) {
        System.out.println(SynchronizedStaticTest.class.getClassLoader());
        try {
            if ("a".equals(flag)) {
                num = 100;
                System.out.println("a set over");
                Thread.sleep(3000);
            } else {
                num = 200;
                System.out.println("b set over");
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(flag + " num = " + num);
    }
}

class Test {
    public static void main(String[] args) throws Exception {
        /* 验证不同的类加载器下，是否会有线程安全问题 */
        //自定义类加载器
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream stream = getClass().getResourceAsStream(fileName);
                if (stream == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[stream.available()];
                    // 将流写入字节数组b中
                    stream.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return super.loadClass(name);
            }
        };

        //使用自定义的类加载器加载类
        Class<?> aClass = classLoader.loadClass("com.zl.thread.synchronizd_volatile.SynchronizedStaticTest");
        Object obj1 = aClass.newInstance();
        System.out.println(aClass.getClassLoader());
        System.out.println(obj1 instanceof SynchronizedStaticTest); //false
        System.out.println("---------------------");
        //使用默认的类加载器加载类
        Object obj2 = new SynchronizedStaticTest();
        Class<?> bClass = obj2.getClass();
        System.out.println(bClass.getClassLoader());
        System.out.println(obj2 instanceof SynchronizedStaticTest); //true
        System.out.println("---------------------");
        System.out.println(aClass == bClass);
        System.out.println("---------------------");

        Method method = aClass.getMethod("test", String.class);
        Runnable r1 = () -> {
//            SynchronizedStaticTest.test("a");
            try {
                method.invoke(obj1, "a");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Runnable r2 = () -> {
            SynchronizedStaticTest.test("b");
        };

        //new Thread(r1).start();
        //new Thread(r2).start();

        SynchronizedStaticTest synchronizedStaticTest = new SynchronizedStaticTest();
        Field field = aClass.getField("object");
        Object o = field.get(obj1);
        if (synchronizedStaticTest.object == SynchronizedStaticTest.object) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

    }
}
