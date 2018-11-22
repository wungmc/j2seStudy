/*
 * Copyright (C), 2011-2018.
 */
package com.wung.event;

import java.util.EventListener;

/**
 * 事件监听器
 *
 * @author wung 2018/11/22.
 */
public class MyEventListener implements EventListener {
	
	public void onAction(MyEvent myEvent) {
		System.out.println("发生事件：" + myEvent.getEventType());
	}
	
}
