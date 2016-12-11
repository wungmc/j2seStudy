package com.wung.properties;

import java.util.Properties;
import java.io.*;

//this class is used to read properties
public class GetProperties {

	private InputStream is;// 用于读取（.properties）文件
	private OutputStream os;// 用于写入（.properties）文件
	private Properties prop;

	public GetProperties() throws Exception {
		prop = new Properties();
	}

	public String getProperties(String propName) {
		try {
			is = getClass().getResourceAsStream("/set.properties");
			prop.load(is);
			return prop.getProperty(propName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setProperties(String propName, String value) {
		try {
			System.out.println("f1:");
			is = getClass().getResourceAsStream("/set.properties");
			prop.load(is);
			is.close();
			prop.setProperty(propName, value);// 插入配置信息
			System.out.println("f2:");
			/*File f=new File("/set.properties");
			if(!f.exists())
			{
				System.out.println("-----file not exist");
				f.createNewFile();
			}
			else
			{
				System.out.println("-----file has exist");
				System.out.println(f.getAbsolutePath());
			}*/
			os = new FileOutputStream("/b.properties");
			prop.store(os, "prop comment");// 插入注释
			System.out.println("f3:");
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
