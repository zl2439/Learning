package com.zl.classloader;

/**
 * @Author: zl
 * @Date: Created in 2019/11/22
 *
 * AppClassLoader和ExtClassLoader加载的类路径
 */
public class LoaderPath {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.ext.dirs"));//ExtClassLoader
        System.out.println(System.getProperty("java.class.path"));//AppClassLoader
    }
}
