package com.wung.designpattern.factorymethod;

/**
 * Created by wung on 2016/5/8.
 */
public class BMWCar extends Car {
    static String brand = "BMW";

    @Override
    public void run() {
        System.out.println(brand + " is run!");
    }
}
