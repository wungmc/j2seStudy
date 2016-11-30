package com.wung.designpattern.factorymethod;

/**
 * 工厂方法模式：
 * 1、一个抽象产品类，可以派生出多个具体的产品类
 * 2、一个抽象工厂，可以派生出多个具体的工厂类
 * 3、每个具体工厂只能生产一个具体的产品。
 *
 * 新增产品时，只需要新增一个产品类和该产品的工厂类，不需要修改原有代码，
 * 即符合开放-封闭原则。
 *
 * Created by wung on 2016/5/8.
 */
public class Client {
    public static void main(String[] args) {
        CarFactory factory = new BMWFactory();
        Car car = factory.createCar();
        car.run();

        factory = new BenziFactory();
        car = factory.createCar();
        car.run();

    }
}
