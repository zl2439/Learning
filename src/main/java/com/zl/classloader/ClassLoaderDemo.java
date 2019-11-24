package com.zl.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: zl
 * @Date: Created in 2019/11/22
 * <p>
 * 自定义类加载器
 */
public class ClassLoaderDemo {
    public static void main(String[] args) throws Exception {
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
        Object obj1 = classLoader.loadClass("com.zl.classloader.ClassLoaderDemo").newInstance();
        System.out.println(obj1.getClass());
        System.out.println(obj1.getClass().getClassLoader());
        System.out.println(obj1 instanceof ClassLoaderDemo); //false
        //使用默认的类加载器加载类
        Object obj2 = new ClassLoaderDemo();
        System.out.println(obj2.getClass());
        System.out.println(obj2.getClass().getClassLoader());
        System.out.println(obj2 instanceof ClassLoaderDemo); //true
    }
}
