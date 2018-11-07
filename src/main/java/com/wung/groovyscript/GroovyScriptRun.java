/*
 * Copyright (C), 2011-2018.
 */
package com.wung.groovyscript;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;


/**
 *
 *
 * @author wung 2018/11/6.
 */
public class GroovyScriptRun {
	
	public static void main(String[] args) throws Exception {
		final GroovyClassLoader classLoader = new GroovyClassLoader();
		
		StringBuilder sb = new StringBuilder();
		sb.append("class Sample {")
				.append("	String sayIt(name) {")
				.append("		\"Groovy says: Hello $name.\"")
				.append("	}")
				.append("}");
		
		Class groovyClass = classLoader.parseClass(sb.toString());
		GroovyObject obj = (GroovyObject) groovyClass.newInstance();
		String out = (String)obj.invokeMethod("sayIt", new Object[]{"jack"});
		System.out.println(out);
		
		File file = new File("src/main/java/com/wung/groovyscript/GroovySample.groovy");
		assert file.exists();
		System.out.println(file.getName());
		
		groovyClass = classLoader.parseClass(file);
		obj = (GroovyObject) groovyClass.newInstance();
		out = (String)obj.invokeMethod("say", new Object[]{"jack", 2});
		System.out.println(out);
		
	}
}
