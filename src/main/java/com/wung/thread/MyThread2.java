package com.wung.thread;

/**
 *
 * Created by wung on 2016/12/4.
 */
public class MyThread2 implements Runnable {
    private String name;
    public MyThread2(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(name + ", i = " + i);
        }
    }
}
