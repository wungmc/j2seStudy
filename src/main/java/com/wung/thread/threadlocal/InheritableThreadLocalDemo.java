/*
 * Copyright (C), 2011-2020.
 */
package com.wung.thread.threadlocal;

/**
 * 使用该类，实现数据由父线程传递到子线程。
 *
 * @author wung 2020-03-31.
 */
public class InheritableThreadLocalDemo {
	
	public static void main(String[] args) {
		final ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
		threadLocal.set("test");
		System.out.println("parent thread: " + Thread.currentThread().getName() + ", value=" + threadLocal.get());
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("sub thread: " + Thread.currentThread().getName() + ", value=" + threadLocal.get());
			}
		}).start();
		
		try {
			Thread.sleep(1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

// out
// parent thread: main, value=test
// sub thread: Thread-0, value=test
