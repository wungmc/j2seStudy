package com.wung.socket;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * Created by wung on 2016/12/17.
 */
public class TCPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("服务端等待连接...");
        Socket client = server.accept();
        PrintStream ps = new PrintStream(client.getOutputStream());
        ps.print("hello world");
        ps.close();
        client.close();
    }
}
