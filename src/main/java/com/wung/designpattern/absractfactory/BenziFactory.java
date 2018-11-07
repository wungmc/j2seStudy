package com.wung.designpattern.absractfactory;

/**
 *
 * Created by wung on 2016/5/8.
 */
public class BenziFactory implements IFactory {
    @Override
    public Car createCar() {
        return new BenziCar();
    }

    @Override
    public Tire createTire() {
        return new BenziTire();
    }
}
