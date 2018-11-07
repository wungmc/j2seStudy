package com.wung.designpattern.absractfactory;

/**
 *
 * Created by wung on 2016/5/8.
 */
public class BenziCar extends Car {
    static String brand = "Benzi";

    @Override
    public void run() {
        System.out.println(brand + " is run!");
    }
}
