package com.wung.i18n;

import java.util.ListResourceBundle;

/**
 *
 * Created by wung on 2016/7/12.
 */
public class Resource_zh_CN extends ListResourceBundle {
    private final Object[][] contents = {
            {"hello", "你好"},
            {"msg", "{0}，您好！今天是 {1}"}
    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
