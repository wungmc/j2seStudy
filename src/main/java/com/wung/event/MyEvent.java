/*
 * Copyright (C), 2011-2018.
 */
package com.wung.event;

import java.util.EventObject;

/**
 * 事件对象
 *
 * @author wung 2018/11/22.
 */
public class MyEvent extends EventObject {
	
	public static final String ONE_EVENT = "one";
	
	public static final String TWO_EVENT = "two";
	
	/**
	 * 事件类型
	 */
	private String eventType;
	
	/**
	 * Constructs a prototypical Event.
	 *
	 * @param source The object on which the Event initially occurred.
	 * @throws IllegalArgumentException if source is null.
	 */
	public MyEvent(Object source) {
		super(source);
	}
	
	public MyEvent(Object source, String eventType) {
		super(source);
		this.eventType = eventType;
	}
	
	public String getEventType() {
		return eventType;
	}
	
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	
}
