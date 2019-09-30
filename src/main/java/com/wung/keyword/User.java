/*
 * Copyright (C), 2011-2019.
 */
package com.wung.keyword;

import java.io.Serializable;

/**
 * @author wung 2019-09-30.
 */
public class User implements Serializable {
	private static final long serialVersionUID = -4084994971999034792L;
	
	private String name = "jack";
	private int age = 18;
	private transient String pwd = "123456";
	
	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", age=" + age +
				", pwd='" + pwd + '\'' +
				'}';
	}
}
