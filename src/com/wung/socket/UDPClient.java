package com.wung.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * Created by wung on 2016/12/17.
 */
public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket; //用于接收数据
        DatagramPacket packet;
        byte[] buf = new byte[1024];

        socket = new DatagramSocket(8888); //监听本机的 8888 端口
        packet = new DatagramPacket(buf, 1024);
        socket.receive(packet);

        String str = new String(packet.getData(), 0, packet.getLength());
        System.out.println(str + ", from " + packet.getAddress().getHostAddress() + ":" + packet.getPort());
        socket.close();

        //output
        //hello, world., from 127.0.0.1:3000
    }
}
