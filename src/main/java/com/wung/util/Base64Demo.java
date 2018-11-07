package com.wung.util;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * sun.* 包属于 sun 公司内部的api，效率不高，而且在以后的 Java 版本中可能不再被支持，所以官方不建议使用；
 * <a>http://www.oracle.com/technetwork/java/faq-sun-packages-142232.html</a>
 * 非 Java 8 可以使用 apache.commons.codec.binary.Base64
 * Java 8 在 java.util 包中提供了 Base64，属于标准用法，而且效率也比前两种高。
 *
 * Created by wung on 2016/12/11.
 */
public class Base64Demo {
    public static void main(String[] args) throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        BASE64Decoder decoder = new BASE64Decoder();
        //String username = new BufferedReader(new InputStreamReader(System.in)).readLine();
        String str = encoder.encode("wungcc".getBytes()); //output : d3VuZ2Nj
        System.out.println(str);
        System.out.println(new String(decoder.decodeBuffer(str))); //output : wungcc


        byte[] encodedBytes = Base64.encodeBase64("ThreadQ1".getBytes());
        System.out.println("encodedBytes " + new String(encodedBytes));
        byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
        System.out.println("decodedBytes " + new String(decodedBytes));
    }
}
