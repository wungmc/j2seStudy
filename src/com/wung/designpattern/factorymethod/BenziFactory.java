package com.wung.designpattern.factorymethod;

/**
 * Created by wung on 2016/5/8.
 */
public class BenziFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new BenziCar();
    }
}
