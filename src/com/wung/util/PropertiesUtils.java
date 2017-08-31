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


    /**
     * 将某对象的 List 集合转换成 Map 集合，key 为对象的 propertyName 属性的值， value 为该对象
     * @param objs
     * @param propertyName
     * @param <V>
     * @param <T>
     * @return
     */
    public static  <V, T> Map<V, T> convertList2Map(List<T> objs, String propertyName) {
        if (objs == null || objs.isEmpty()) {
            return Collections.emptyMap();
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

        Map<V, T> map = new HashMap<>(objs.size());
        for (T obj : objs) {
            try {
                V value = (V)propertyMethod.invoke(obj);
                if (value != null) {
                    map.put(value, obj);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return Collections.emptyMap();
            }
        }

        return map;
    }


    /**
     * 判断对象是否为空，复杂对象时：如果有属性不为空，则对象不为空
     * 注意：不支持属性为复杂对象的校验
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(T obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String && obj.equals("")) {
            return true;
        }

        Method[] methods = obj.getClass().getDeclaredMethods();
        if (methods.length == 0) {
            return true;
        }
        try {
            for (Method method : methods) {
                if (method.getName().startsWith("get")) {
                    Object value = method.invoke(obj);
                    if (value != null && !"".equals(value)) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }

        return true;
    }
}
