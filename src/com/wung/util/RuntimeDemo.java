package com.wung.util;

import java.io.IOException;

/**
 *
 * Created by wung on 2016/12/6.
 */
public class RuntimeDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();

        // the number of processors available to the Java virtual machine
        System.out.println(rt.availableProcessors());

        // Executes the specified string command
        Process pro = rt.exec("calc");
        pro.waitFor(); //causes the current thread to wait

        String str = null;
        for (int i = 0; i < 1000; i++) {
            str = str + i;
        }

        System.out.println(rt.maxMemory());
        System.out.println(rt.totalMemory());
        System.out.println(rt.freeMemory());
        rt.gc();
        System.out.println(rt.freeMemory());

        // a nonzero status code indicate abnormal termination.
        rt.exit(0);
    }
}
