/*
 * Copyright (C), 2011-2018.
 */
package com.wung.event;

import java.util.EventListener;
import java.util.HashSet;
import java.util.Set;

/**
 * 事件源。
 * 产生事件的地方，同时也是事件监听器注册的地方。
 *
 * @author wung 2018/11/22.
 */
public class MyEventSource {
	
	/**
	 * 监听器集合
	 */
	private Set<MyEventListener> listenerList = new HashSet<>();
	
	/**
	 * 注册监听器
	 *
	 * @param listener
	 */
	public void addEventListener(MyEventListener listener) {
		if (listener != null) {
			listenerList.add(listener);
		}
	}
	
	public void removeEventListener(MyEventListener listener) {
		listenerList.remove(listener);
	}
	
	/**
	 * 事件 one 发生
	 */
	public void eventOne() {
		MyEvent myEvent = new MyEvent(this, MyEvent.ONE_EVENT);
		doOnAction(myEvent);
	}
	
	/**
	 * 事件 two 发生
	 */
	public void eventTwo() {
		MyEvent myEvent = new MyEvent(this, MyEvent.TWO_EVENT);
		doOnAction(myEvent);
	}
	
	/**
	 * 将事件发给所有监听器
	 *
	 * @param myEvent
	 */
	public void doOnAction(MyEvent myEvent) {
		for (MyEventListener listener : listenerList) {
			listener.onAction(myEvent);
		}
	}
	
	
	
}
