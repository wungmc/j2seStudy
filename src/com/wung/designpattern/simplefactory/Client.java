package com.wung.designpattern.simplefactory;

/**
 *
 * Created by wung on 2016/5/8.
 */
public class Client {
    public static void main(String[] args) {
        Car car = CarFactory.createCar("Benzi");
        car.run();

        car = CarFactory.createCar("BMW");
        car.run();
    }
}
