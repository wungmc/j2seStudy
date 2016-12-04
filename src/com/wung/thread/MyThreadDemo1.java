package com.wung.thread;

/**
 *
 * Created by wung on 2016/12/4.
 */
public class MyThreadDemo1 {
    public static void main(String[] args) {
        MyThread t1 = new MyThread("Thread 1");
        MyThread t2 = new MyThread("Thread 2");
        t1.start();
        t2.start();
    }
}
