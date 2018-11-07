package com.wung.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * Created by wung on 2016/11/28.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    public String value();
}
