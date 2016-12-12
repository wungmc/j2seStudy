package com.wung.socket;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient extends Frame {

	private static final long serialVersionUID = 1L;
	
	private Socket s = null;
	private DataOutputStream dos = null;
	private DataInputStream dis = null;
	private boolean bconnected = false;
	
	private TextArea ta = new TextArea(); //显示聊天内容
	private TextField tf = new TextField();//输入框
	
	public ChatClient(String name){
		super(name);
	}
	
	public static void main(String[] args) {
		new ChatClient("chat").launch();
	}

	public void launch(){
		setLocation(400, 300);
		setSize(300, 600);
		ta.setEditable(false);
		this.add(ta, BorderLayout.NORTH);
		this.add(tf, BorderLayout.SOUTH);
		this.addWindowListener(new WindowAdapter() { //关闭窗口
			@Override
			public void windowClosing(WindowEvent e) {
				disconnect(); //释放资源
				System.exit(0);
			}
		});
		tf.addActionListener(new TFListener());
		pack();
		setVisible(true);
		
		connect();
		new Thread(new ReceiveThread()).start();
	}
	
	//连接服务器端
	public void connect() {
		try {
			s = new Socket("127.0.0.1", 8888);
			System.out.println("connected!");
			bconnected = true;
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
		} catch (UnknownHostException e) {
			System.out.println("连接服务器失败...");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//关闭连接
	public void disconnect() {		
		try {
			bconnected = false;
			if (dos != null) 
				dos.close();
			if (dis != null)
				dis.close();
			if (s != null) 
				s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//TextField的监听器
	private class TFListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String info = tf.getText();
			tf.setText("");
			sendInfo(info);
		}
		
		public void sendInfo(String info) {
			try {
				dos.writeUTF(info);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
	
	//用于接受服务器发送来的信息
	private class ReceiveThread implements Runnable {
		@Override
		public void run() {
			try {
				while (bconnected) {
					ta.append("\n" + dis.readUTF());
				}
			} catch (SocketException se) {
				System.out.println("bye!");
			} catch (EOFException ee) {
				System.out.println("bye-bye!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
}
