package com.wung.socket;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	boolean started = false;
	ServerSocket ss = null;
	List<Client> clients = new ArrayList<Client>();

	public static void main(String[] args) {
		new ChatServer().start();
	}
	
	public void start() {		
		try {
			ss = new ServerSocket(8888);
			started = true;
		} catch (BindException e) {
			System.out.println("端口被占用....");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			while (started) {
				Socket s = ss.accept();
				System.out.println("a client connected!");
				Client c = new Client(s);
				clients.add(c);
				new Thread(c).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
			
	class Client implements Runnable {
		private Socket s = null;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		private boolean bconnected = false;
		
		public Client(Socket s) {
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				bconnected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void send(String info) {
			try {
				dos.writeUTF(info);
			} catch (IOException e) {
				clients.remove(this);
				System.out.println("对方退出了，我从list里面去掉了！");
			}
		}
		
		@Override
		public void run() {
			try {
				while (bconnected) {	
					String info = dis.readUTF();
					System.out.println(info);
					
					//接收到客户端发来的信息后，将该信息转发给每个客户端
					for (int i = 0; i < clients.size(); i++) {
						Client c = clients.get(i);
						c.send(info);
					}
										
					/*
					 * 该方法会锁定clients，效率较低
					for (Iterator<Client> it = clients.iterator(); it.hasNext(); ) {
						Client c = it.next();
						c.send(info);
					}
					*/
				}
			} catch (EOFException e) {
				System.out.println("client close!");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {		
				try {
					if (dis != null) 
						dis.close();
					if (dos != null)
						dos.close();
					if (s != null) 
						s.close();
				} catch (IOException e) {
					e.printStackTrace();	
				}			
			}
		}		
	}
}
