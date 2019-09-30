/*
 * Copyright (C), 2011-2019.
 */
package com.wung.keyword;

import java.io.*;

/**
 * transient 关键字：修饰变量，被修饰的变量不参与序列化。
 *
 * @author wung 2019-09-30.
 */
public class TransientDemo {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		User user = new User();
		System.out.println("序列化之前：" + user);
		
		TransientDemo.writeObject(user);
		TransientDemo.readObject();
	}
	
	private static void writeObject(User user) throws IOException {
		FileOutputStream fos = new FileOutputStream("src/main/java/com/wung/keyword/transient");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(user);
		
		fos.close();
		oos.close();
	}
	
	private static void readObject() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("src/main/java/com/wung/keyword/transient");
		ObjectInputStream ois = new ObjectInputStream(fis);
		User user = (User)ois.readObject();
		System.out.println("序列化之后：" + user);
		
		fis.close();
		ois.close();
	}
	
}

// out
// 序列化之前：User{name='jack', age=18, pwd='123456'}
// 序列化之后：User{name='jack', age=18, pwd='null'}
