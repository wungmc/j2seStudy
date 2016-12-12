package com.wung.properties;

import java.io.FileOutputStream;
import java.util.Properties;
import java.io.FileInputStream;

public class SetProperties {

	/**
	 * 对属性文件（xx.properties）的操作
	 * 注：属性文件一定要放在当前工程的根目录下，也就是放在与src目录在同一个目录下（我的JDevelop 是这样的）
	 */
	public SetProperties() {

	}

	/**
	 * 采用Properties类取得属性文件对应值
	 *
	 * @param propertiesFileName properties文件名，如a.properties
	 * @param propertyName 属性名
	 * @return 根据属性名得到的属性值，如没有返回""
	 */
	public String getProperties(String propertiesFileName,
								String propertyName) {
		String s = "";
		Properties p = new Properties();// 加载属性文件读取类
		FileInputStream in;
		try {
			// propertiesFileName如test.properties
			in = new FileInputStream(propertiesFileName);// 以流的形式读入属性文件
			p.load(in);// 属性文件将该流加入的可被读取的属性中
			in.close();// 读完了关闭
			s = p.getProperty(propertyName);// 取得对应的属性值
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}


	/**
	 * 更改属性文件的值，如果对应的属性不存在，则自动增加该属性
	 *
	 * @parampropertiesFileNameproperties文件名，如a.properties
	 * @parampropertyName属性名
	 * @parampropertyValue将属性名更改成该属性值
	 * @return是否操作成功
	 */
	public boolean setProperties(String propertiesFileName,
								 String propertyName, String propertyValue) {
		boolean writeOK = true;
		Properties p = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(propertiesFileName);
			p.load(in);//
			in.close();
			p.setProperty(propertyName, propertyValue);// 设置属性值，如不属性不存在新建
			// p.setProperty("testProperty","testPropertyValue");
			FileOutputStream out = new FileOutputStream(propertiesFileName);// 输出流
			p.store(out, "");// 设置属性头，如不想设置，请把后面一个用""替换掉
			out.flush();// 清空缓存，写入磁盘
			out.close();// 关闭输出流
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writeOK;

	}
}
