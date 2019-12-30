package com.zl.gc;

import java.util.ArrayList;
import java.util.Date;

/**
 * @Author: zl
 * @Date: Created in 2019/12/15
 * <p>
 * 垃圾回收器
 * -Xms30M
 * -Xmx30M
 * -Xmn10M
 * -XX:SurvivorRatio=8
 * -XX:+PrintGCDetails
 * -XX:+UseSerialGC
 * -XX:+UseParNewGC
 * -XX:+UseParallelGC
 * -XX:+UseParallelOldGC
 * -XX:+UseConcMarkSweepGC
 * -XX:+UseG1GC
 *
 */
public class GCTest {
    private static final int KB = 1024;

    private byte[] buffer = new byte[1024 * KB];

    public static void main(String[] args) throws Exception {
        //outOfMemory();
        GCTest gcTest = new GCTest();
        System.gc();
    }

    /*private static void outOfMemory() throws Exception{
        ArrayList<Object> list = new ArrayList<>();
        while (true){
            System.out.println(new Date().getTime());
            GCTest gcTest = new GCTest();
            list.add(gcTest);
            Thread.sleep(1000);
        }
    }*/
}
