package com.wung.annotation;

/**
 *
 * Created by wung on 2016/11/28.
 */
public class AnnotationDemo {
    @Override
    @MyAnnotation("wung")
    public String toString() {
        return "hello, annotation";
    }
}
