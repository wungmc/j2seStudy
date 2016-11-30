package com.wung.designpattern.absractfactory;

/**
 * 工厂方法模式：
 * 1、一个抽象产品类，可以派生出多个具体的产品类
 * 2、一个抽象工厂，可以派生出多个具体的工厂类
 * 3、每个具体工厂只能生产一个具体的产品。
 *
 * 新增产品时，只需要新增一个产品类和该产品的工厂类，不需要修改原有代码，
 * 即符合开放-封闭原则。
 *
 * 抽象工厂模式：
 * 1、多个抽象产品类，分别可以派生出多个具体的产品类
 * 2、一个抽象工厂接口，可以派生出多个具体的工厂类
 * 3、每个具体的工厂都可以生产多个具体产品
 *
 * 抽象工厂模式解决的是产品簇的问题，每个具体工厂都可以生产一个产品簇（一系列有相关或依赖关系的产品，
 * 如本例中的 Car 和 Tire）。
 * 工厂方法模式适合只有一种产品的问题。
 *
 * 抽象工厂模式中新增一个具体工厂非常简单，但是新增一个产品则非常麻烦，这也是其缺点。
 *
 * Created by wung on 2016/5/8.
 */
public class Client {
    public static void main(String[] args) {
        IFactory factory = new BMWFactory();
        Car car = factory.createCar();
        Tire tire = factory.createTire();
        car.run();
        tire.print();

        // 更换工厂后，所有产品都更换了
        factory = new BenziFactory();
        car = factory.createCar();
        tire = factory.createTire();
        car.run();
        tire.print();
    }
}
