/*
 * Copyright (C), 2011-2020.
 */
package com.wung.collection;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 测试 LinkedHashMap 按访问顺序进行排序的特性。accessOrder 默认为 false
 *
 * 利用该特性，及重写 removeEldestEntry 方法，可以实现一个 LRU 缓存。
 *
 * @author wung 2020-03-06.
 */
public class LinkedHashMapDemo {
	
	public static void main(String[] args) {
		// 想要支持访问顺序，必须使用这个构造函数
		// 将 accessOrder 参数设置为 true
		Map<String, String> map = new LinkedHashMap<>(16,0.75f,true);
		map.put("apple", "苹果");
		map.put("watermelon", "西瓜");
		map.put("banana", "香蕉");
		map.put("peach", "桃子");
		
		iter(map);
		
		map.get("banana");
		map.get("apple");
		
		System.out.println("\n访问后的顺序：");
		iter(map);
	}
	
	static void iter(Map<String, String> map) {
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	}
}
