/*
 * Copyright (C), 2011-2020.
 */
package com.wung.collection;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于 LinkedHashMap 实现的 LRU（Least Recently Used 最近最少使用） 缓存。
 *
 * 该代码来自 dubbo 框架。
 *
 * @author wung 2020-03-08.
 */
public final class LRUCache<K, V> extends LinkedHashMap<K, V> {
	private static final long serialVersionUID = 712789399110929318L;
	
	private static final int DEFAULT_MAX_CAPACITY = 1000;
	private static final float DEFAULT_LOAD_FACTORY = 0.75f;
	private int maxCapacity;
	
	private final Lock lock = new ReentrantLock();
	
	public LRUCache() {
		this(DEFAULT_MAX_CAPACITY);
	}
	
	public LRUCache(int maxCapacity) {
		// 初识大小16，每次扩容0.75，开启访问顺序开关
		super(16, DEFAULT_LOAD_FACTORY, true);
		this.maxCapacity = maxCapacity;
	}
	
	@Override
	public boolean containsKey(Object key) {
		try {
			lock.lock();
			return super.containsKey(key);
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	public V put(K key, V value) {
		try {
			lock.lock();
			return super.put(key, value);
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	public V get(Object key) {
		try {
			lock.lock();
			return super.get(key);
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return size() > maxCapacity;
	}
	
	@Override
	public int size() {
		try {
			lock.lock();
			return super.size();
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	public V remove(Object key) {
		try {
			lock.lock();
			return super.remove(key);
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	public void clear() {
		try {
			lock.lock();
			super.clear();
		} finally {
			lock.unlock();
		}
	}
	
	public int getMaxCapacity() {
		return maxCapacity;
	}
	
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
}
