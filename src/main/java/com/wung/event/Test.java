/*
 * Copyright (C), 2011-2018.
 */
package com.wung.event;

/**
 * 测试 java 的事件监听机制。
 * 1、事件源：产生事件的地方，同时也是事件监听器注册的地方
 * 2、事件：封装事件的一些信息。
 * 3、触发：外部触发事件源的某个方法，产生某个事件，然后将该事件通知所有监听器。
 *
 * @author wung 2018/11/22.
 */
public class Test {
	
	public static void main(String[] args) {
		MyEventSource source = new MyEventSource();
		source.addEventListener(new MyEventListener());
		
		source.eventOne();
		source.eventTwo();
		
		// out
		// 发生事件：one
		// 发生事件：two
	}
	
}
