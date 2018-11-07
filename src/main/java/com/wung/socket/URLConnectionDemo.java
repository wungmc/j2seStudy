package com.wung.socket;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * URL & URLConnection
 * Created by wung on 2016/12/15.
 */
public class URLConnectionDemo {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http", "www.kongzhong.com", 80, "/news");
//        URL url = new URL("http", "www.kongzhong.com", "/news");
        InputStream input = url.openStream();
        Scanner scanner = new Scanner(input);
        scanner.useDelimiter("\n");
        // 输出整个网页的源代码
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }

        //URLConnection
        URL url2 = new URL("http://www.runoob.com/");
        URLConnection conn = url2.openConnection();
        System.out.println("内容大小：" + conn.getContentLength());
        System.out.println("内容类型：" + conn.getContentType());
        //output
        //内容大小：12263
        //内容类型：text/html; charset=utf-8
    }
}
