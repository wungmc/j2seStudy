package com.wung.thread;

/**
 *
 * Created by wung on 2016/12/4.
 */
class MyThread3 implements Runnable {
    private int ticket;
    public MyThread3(int ticket) {
        this.ticket = ticket;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (this.ticket > 0) {
                System.out.println("ticket = " + ticket--);
            }
        }
    }
}
