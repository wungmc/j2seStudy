package com.wung.socket;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URLEncoder: for HTML form encoding.This class contains static methods for converting a String to the application/x-www-form-urlencoded MIME format.
 * URLDecoder: This class contains static methods for decoding a String from the application/x-www-form-urlencoded MIME format.
 *
 * Created by wung on 2016/12/17.
 */
public class URLEncoderAndURLDecoderDemo {
    public static void main(String[] args) throws Exception {
        String str = "Hello, 世界";
        String enstr = URLEncoder.encode(str, "UTF-8");
        System.out.println(enstr);
        String enstr2 = URLEncoder.encode(str);
        System.out.println(enstr2);
        //output
        //Hello%2C+%E4%B8%96%E7%95%8C
        //Hello%2C+%E4%B8%96%E7%95%8C

        System.out.println(URLDecoder.decode(enstr, "UTF-8"));
        //output
        //Hello, 世界
    }
}
