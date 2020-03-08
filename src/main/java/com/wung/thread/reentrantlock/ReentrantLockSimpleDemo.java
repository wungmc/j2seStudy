/*
 * Copyright (C), 2011-2020.
 */
package com.wung.thread.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 简单使用示例。
 *
 * @author wung 2020-03-06.
 */
public class ReentrantLockSimpleDemo {
	
	private static final Lock lock = new ReentrantLock();
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				test();
			}
		}, "线程1").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				test();
			}
		}, "线程2").start();
	}
	
	static void test() {
		try {
			lock.lock();
			System.out.println(Thread.currentThread().getName() + " 获取锁成功！");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println(Thread.currentThread().getName() + " 释放锁成功！");
		}
	}
	
}
