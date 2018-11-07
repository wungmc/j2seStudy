// package com.wung.util;
//
// import com.danga.MemCached.MemCachedClient;
// import com.danga.MemCached.SockIOPool;
// import org.apache.log4j.Logger;
//
// import java.util.Date;
//
// /**
//  * @author
//  *
//  */
// public final class CacheUtil {
// 	private final static Logger log = Logger.getLogger(CacheUtil.class);
//
// 	private static CacheUtil instance = null;
// 	private SockIOPool pool;
// 	private MemCachedClient mcc;
//
// 	private CacheUtil() {
// 		this.init();
// 	}
// 	/**
// 	 * 设置缓存过期时间
// 	 * @param haomiao 毫秒数
// 	 * @return
// 	 */
// 	public Date getCacheExpireTime(long haomiao){
// 		long curtime=new Date().getTime();
// 		return new Date(curtime+haomiao);
// 	}
// 	/**
// 	 *
// 	 */
// 	private void init() {
// 		try {
// 			pool = SockIOPool.getInstance();
// 			// 开始设置pool属性
// 			String[] servers = {
// 					"127.0.0.1:12000",
// 					"127.0.0.1:12000",
// 					"127.0.0.1:12000"
// 			};
// 			// grab an instance of our connection pool
// 			pool.setServers(servers);
// 			// set some basic pool settings
// 			pool.setInitConn(10);
// 			pool.setMinConn(10);
// 			pool.setMaxConn(800);
// 			pool.setMaxIdle(1000 * 60 * 60 * 6);
//
// 			// set the sleep for the maint thread
// 			// it will wake up every x seconds and
// 			// maintain the pool size
// 			pool.setMaintSleep(30);
//
// 			// set some TCP settings
// 			// disable nagle
// 			// set the read timeout to 3 secs
// 			// and don't set a connect timeout
// 			pool.setNagle(false);
// 			pool.setSocketTO(3000);
// 			pool.setSocketConnectTO(0);
//
// 			// initialize the connection pool
// 			pool.initialize();
//
// 			// get client instance
// 			mcc = new MemCachedClient();
// 			// 开始设置 mcc属性
// 			// lets set some compression on for the client
// 			// compress anything larger than 64k
// 			mcc.setCompressEnable(true);
// 			mcc.setCompressThreshold(64 * 1024);
// 		} catch (Exception e) {
// 			log.error("memcache read file p2p.properties error:" + e);
// 		}
// 	}
//
// 	public static CacheUtil getInstance() {
// 		if (instance == null) {
// 			synchronized (log) {
// 				instance = new CacheUtil();
// 			}
// 		}
// 		return instance;
// 	}
//
// 	public String getCache(String key) {
// 		if (log.isDebugEnabled()) {
// 			log.debug("memcache get key: " + key);
// 		}
// 		return (String) mcc.get(key);
// 	}
//
// 	public Object getCacheObject(String key) {
// 		if (log.isDebugEnabled()) {
// 			log.debug("memcache get key: " + key);
// 		}
// 		return mcc.get(key);
// 	}
//
// 	public boolean putCache(String key, String value, Date expireTime) {
// 		if (CacheKey.IS_START_CACHE == false) {
// 			return false;
// 		}
// 		if (log.isDebugEnabled()) {
// 			log.debug("memcache put key:" + key + " value:" + value);
// 		}
// 		return mcc.set(key, value, expireTime);
// 	}
//
// 	public boolean putCache(String key, Object value, Date expireTime) {
// 		if (CacheKey.IS_START_CACHE == false) {
// 			return false;
// 		}
// 		if (log.isDebugEnabled()) {
// 			log.debug("memcache put key:" + key + " value:" + value);
// 		}
// 		//System.out.println(key+"=="+value);
// 		return mcc.set(key, value, expireTime);
// 	}
//
// 	public boolean putCache(String key, Object value) {
// 		if (CacheKey.IS_START_CACHE == false) {
// 			return false;
// 		}
// 		if (log.isDebugEnabled()) {
// 			log.debug("memcache put key:" + key + " value:" + value);
// 		}
// //		System.out.println(key+"=="+value);
// 		return mcc.set(key, value);
// 	}
//
// 	public boolean delCache(String key) {
// 		if (CacheKey.IS_START_CACHE == false) {
// 			return false;
// 		}
// 		if (log.isDebugEnabled()) {
// 			log.debug("memcache del key:" + key);
// 		}
// 		return mcc.delete(key);
// 	}
// }
