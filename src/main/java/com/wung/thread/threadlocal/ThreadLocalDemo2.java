/*
 * Copyright (C), 2011-2020.
 */
package com.wung.thread.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池化时，因为线程是复用的，所以之前的利用 InheritableThreadLocal 进行父子之间的值传递还是有问题。
 *
 * @author wung 2020-03-31.
 */
public class ThreadLocalDemo2 {
	// 创建一个只有 1 个线程的线程池
	private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(1);
	
	public static void main(String[] args) {
		System.out.println("main Thread:" + Thread.currentThread().getName());
		final ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
		
		threadLocal.set("AAA");
		
		EXECUTOR_SERVICE.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread name = " + Thread.currentThread().getName() + ", value=" + threadLocal.get());
			}
		});
		
		// 主线程暂停一段时间，让上面的那个任务跑完
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 修改主线程中的 threadLocal 值
		threadLocal.set("BBB");
		
		EXECUTOR_SERVICE.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread name = " + Thread.currentThread().getName() + ", value=" + threadLocal.get());
			}
		});
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
