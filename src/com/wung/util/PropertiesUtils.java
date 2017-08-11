/**
 * Copyright (C), 2011-2017, 微贷网.
 */
package com.wung.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 对象属性操作工具类
 *
 * @author wungmc 2017/8/12.
 */
public class PropertiesUtils {

    /**
     * 从某个对象的 List 集合中获取对象的某个属性的 List 集合
     *
     * @param objs
     * @param propertyName
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> List<V> getPropertyList(List<T> objs, String propertyName) {
        if (objs == null || objs.isEmpty()) {
            return Collections.emptyList();
        }

        Class<?> clazz = objs.get(0).getClass();
        Field field = getField(clazz, propertyName);
        if (field == null) {
            throw new RuntimeException(propertyName + " 属性不存在！");
        }

        Method propertyMethod = getGetMethod(clazz, propertyName);
        if (propertyMethod == null) {
            throw new RuntimeException(propertyName + " 属性没有 public 类型的 get 方法！");
        }

        Set<V> propertiesValues = new HashSet<>();
        for (T obj : objs) {
            try {
                propertiesValues.add((V)propertyMethod.invoke(obj));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return Collections.emptyList();
            }
        }
        return new ArrayList<>(propertiesValues);
    }

    /**
     * 获取属性对象
     * @param clazz
     * @param propertyName
     * @return
     */
    private static Field getField(Class<?> clazz, String propertyName) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(propertyName);
        } catch (NoSuchFieldException e) {}

        if (field == null) {
            try {
                field = clazz.getField(propertyName);
            } catch (NoSuchFieldException e) {}
        }
        return field;
    }

    /**
     * 获取 get 方法对象
     * @param clazz
     * @param propertyName
     * @return
     */
    private static Method getGetMethod(Class<?> clazz, String propertyName) {
        String methodName = "get" + propertyName;
        Method[] methods = clazz.getDeclaredMethods();
        if (methods.length == 0) {
            return null;
        }
        for (Method method : methods) {
            if (methodName.toUpperCase().equals(method.getName().toUpperCase())) {
                return method;
            }
        }
        return null;
    }
}
