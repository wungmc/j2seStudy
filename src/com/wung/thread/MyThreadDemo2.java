package com.wung.thread;

/**
 *
 * Created by wung on 2016/12/4.
 */
public class MyThreadDemo2 {
    public static void main(String[] args) {
        MyThread t1 = new MyThread("Thread 1");
        MyThread t2 = new MyThread("Thread 2");
        new Thread(t1).start();
        new Thread(t2).start();
    }
}
