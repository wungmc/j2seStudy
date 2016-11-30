package com.wung.i18n;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * Created by wung on 2016/7/12.
 */
public class LocaleTest {

    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle("com.wung.i18n.Resource", locale);
        System.out.println(rb.getString("hello"));

        String msg = rb.getString("msg");
        System.out.println(MessageFormat.format(msg, "张三", new Date()));
    }
}
