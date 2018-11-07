package com.wung.thread;

/**
 *
 * Created by wung on 2016/12/4.
 */
public class MyThreadDemo3 {
    public static void main(String[] args) {
        MyThread3 t1 = new MyThread3(5);
        new Thread(t1).start();
        new Thread(t1).start();
        new Thread(t1).start();
    }
}
