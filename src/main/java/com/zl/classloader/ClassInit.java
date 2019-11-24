package com.zl.classloader;

/**
 * @Author: zl
 * @Date: Created in 2019/11/20
 * <p>
 * 获取类加载器
 */
public class ClassInit {
    public static void main(String[] args) {
        //获取类加载器
        ClassLoader classLoader = ClassInit.class.getClassLoader();
        //System.out.println(classLoader);
        while (classLoader != null) {
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }
    }
}
