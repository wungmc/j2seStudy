package com.wung.socket;

import java.net.InetAddress;

/**
 * This class represents an Internet Protocol (IP) address.
 * An instance of an InetAddress consists of an IP address and possibly its corresponding host name.
 * Subclasses: Inet4Address, Inet6Address
 *
 * Created by wung on 2016/12/12.
 */
public class InetAddressDemo {
    public static void main(String[] args) throws Exception {
        InetAddress local = InetAddress.getLocalHost();
        System.out.println("本机别名：" + local.getHostName());
        System.out.println("本机名：" + local.getCanonicalHostName());
        System.out.println("本机IP地址：" + local.getHostAddress()); //本机IP地址：192.168.56.1

        // 通过域名获得 InetAddress 对象
        InetAddress remot = InetAddress.getByName("www.baidu.com");
        //该地址是否可达
        System.out.println(remot.isReachable(500)); //false ????
        System.out.println(remot.getHostAddress()); //119.75.218.70
        // 不需要访问DNS服务器，直接返回域名
        System.out.println(remot.getHostName()); //www.baidu.com
        System.out.println(remot.getCanonicalHostName()); //119.75.218.70

        // 通过ip地址获得 InetAddress 对象
        InetAddress inet = InetAddress.getByAddress(new byte[] {(byte) 119, (byte) 75, (byte) 218, (byte) 70});
        // 需要访问 DNS
        System.out.println(inet.getHostName()); //119.75.218.70
        System.out.println(inet.getHostAddress());//119.75.218.70
        System.out.println(inet.getCanonicalHostName());//119.75.218.70
    }
}
