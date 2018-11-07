package com.wung.questions;

/**
 * 先让子线程运行5次，然后让主线程运行10次，然后再让子线程运行5次，
 * 然后主线程运行10次，如此循环3次
 * @author 
 *
 */
public class ThreadQ1 {
	public static void main(String[] args) {
		System.out.println("hhh");
		new ThreadQ1().init();
	}
	
	public void init() {
		final Bussiness b = new Bussiness();
		new Thread(
				new Runnable() {	
					@Override
					public void run() {
						for (int i = 0; i < 3; i++) {
							b.subThread(i);
						}
						
					}
				}
		).start();
		
		for (int i = 0; i < 3; i++) 
			b.mainThread(i);
	}
	
	private class Bussiness {
		boolean flag = true;

		public synchronized void mainThread(int i) {
			if (flag) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					System.out.println("线程切换。。。");
				}
			}
				
			for (int j = 0; j < 10; j++) {
				System.out.println(Thread.currentThread().getName() + ":i=" + i + ", j=" + j);
			}
				
			flag = true;
			this.notify();
		}
		
		public synchronized void subThread(int i) {
			if (!flag) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					System.out.println("线程切换。。。");
				}
			}
				
			for (int j = 0; j < 5; j++) {
				System.out.println(Thread.currentThread().getName() + ":i=" + i + ", j=" + j);
			}
				
			flag = false;
			this.notify();
		}
	}
}
