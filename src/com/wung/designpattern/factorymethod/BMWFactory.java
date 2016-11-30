package com.wung.designpattern.factorymethod;

/**
 * Created by wung on 2016/5/8.
 */
public class BMWFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new BMWCar();
    }
}
