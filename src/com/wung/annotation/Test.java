package com.wung.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 *
 * Created by wung on 2016/11/28.
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clazz = null;
        clazz = Class.forName("com.wung.annotation.AnnotationDemo");
        Method m = clazz.getMethod("toString");
        if (m.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annos = m.getAnnotation(MyAnnotation.class);
            System.out.println(annos.value());
        }
    }
}
