package com.wung.designpattern.simplefactory;

/**
 *
 * Created by wung on 2016/5/8.
 */
public class CarFactory {
    public static Car createCar(String brand) {
        if ("BMW".equals(brand)) {
            return new BMWCar();
        }
        if ("Benzi".equals(brand)) {
            return new BenziCar();
        }
        // 如果新增了产品，则需要修改这里，违反了开发-封闭原则
        return null;

    }
}
