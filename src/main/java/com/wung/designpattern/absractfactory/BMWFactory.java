package com.wung.designpattern.absractfactory;

/**
 * Created by wung on 2016/5/8.
 */
public class BMWFactory implements IFactory {
    @Override
    public Car createCar() {
        return new BMWCar();
    }

    @Override
    public Tire createTire() {
        return new BMWTire();
    }
}
