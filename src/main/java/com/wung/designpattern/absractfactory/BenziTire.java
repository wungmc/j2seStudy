package com.wung.designpattern.absractfactory;

/**
 * Created by wung on 2016/5/8.
 */
public class BenziTire extends Tire {
    static String name = "Benzi Tire";

    @Override
    public void print() {
        System.out.println(name);
    }
}
