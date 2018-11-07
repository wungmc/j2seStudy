package com.wung.charset;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 *
 * Created by wung on 2016/7/20.
 */
public class CharsetDemo {
    public static void main(String[] args) throws Exception {
        CharsetDemo.Demo2();
    }

    public static void Demo1() throws Exception {
        String file = "c:/stream.txt";
        String charset = "utf-8";

        //写字符转换成字节流
        FileOutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, charset);
        try {
            writer.write("写字符转换成字节流");
        }
        finally {
            writer.close();
        }

        //读取字节转换成字符流
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream, charset);
        char[] buff = new char[8];
        int count = 0;
        try {
            while ((count = reader.read(buff)) != -1) {
                System.out.println(String.valueOf(buff, 0 ,count) + ", " + count);
            }
        } finally {
            reader.close();
        }

    }

    public static void Demo2() throws Exception {
        //System.getProperties().list(System.out);
        System.out.println(System.getProperty("file.encoding"));
        String s = "写字符转换成字节流";
        byte[] b = s.getBytes();
//        for (byte by : b) {
//            System.out.println(by);
//        }
        System.out.println(new String(b, "utf-8"));

//        Charset charset = Charset.defaultCharset();
        Charset charset = Charset.forName("utf-8");
        System.out.println(charset.displayName());
        ByteBuffer bb = charset.encode(s);
        System.out.println(new String(bb.array()));
        CharBuffer cb = charset.decode(bb);
        System.out.println(cb.toString());
    }
}
