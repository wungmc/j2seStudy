package com.wung.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 不断接收客户端的消息，在消息前加上“echo: ”后返回。
 *
 * Created by wung on 2016/12/17.
 */
public class EchoServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server;
        Socket client;
        PrintStream print;
        BufferedReader reader;

        server = new ServerSocket(8888);
        boolean flag = true;
        while (flag) {
            System.out.println("服务端等待连接...");
            client = server.accept();
            print = new PrintStream(client.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            boolean f = true;
            while (f) {
                String str = reader.readLine();
                if (str == null || str.isEmpty()) {
                    System.out.println(flag);
                    f = false;
                }
                else {
                    if ("bye".equals(str)) {
                        f = false;
                    }
                    else {
                        print.println("echo: " + str);
                    }
                }
            }
            print.close();
            reader.close();
            client.close();
        }
        server.close();
    }
}
