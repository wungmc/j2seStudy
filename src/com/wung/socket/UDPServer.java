package com.wung.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * Created by wung on 2016/12/17.
 */
public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket;
        socket = new DatagramSocket(3000); //服务端从 3000 端口等待发送数据

        DatagramPacket packet;
        String msg = "hello, world.";
        packet = new DatagramPacket(msg.getBytes(), 0, msg.length()); //要发送的数据
        packet.setAddress(InetAddress.getByName("localhost")); //数据发到 localhost
        packet.setPort(8888); //发到目的机器的 8888 端口

        socket.send(packet);
        socket.close();
    }
}
