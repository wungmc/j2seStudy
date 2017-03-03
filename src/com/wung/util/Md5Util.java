package com.wung.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
	public static String Md5(String mingwen, String disturbbefore, String disturbafter) {
		try {
			String str = disturbbefore + mingwen + disturbafter;
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			//为摘要对象提供数据
			md.update(str.getBytes()); 
			//计算摘要（hash value），长度16的字节数组（16*8=128位）
			byte b[] = md.digest(); 
			
			//将每个字节都转换成16进制，拼接成32位的字符串（简短，方便使用）
			//java中需要自己手动转换成16进制，所以md5加密方法才有下面的代码
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) { 
				/*
				 * 0xff(十六进制)，其为int类型，所以其二进制为：0000 0000 0000 0000 0000 0000 1111 1111
				 * & 0xff 意思是取低8位
				 */
				int i = b[offset] & 0xff; //相当于i += 256;
				buf.append(String.format("%02x", i));
				
				//这种转换方式也可以
				//if(i<16) 
				//	buf.append("0");
				//String s = Integer.toHexString(i);
				
			} 
			return buf.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String Md5(String mingwen) {
		return Md5(mingwen, "", "");
	}
	
	public static void main(String[] args) {
//		System.out.println(0xff);//255
//		System.out.println(Integer.toHexString((byte)-2 & 0xFF));//255
//		System.out.println(Integer.toBinaryString((byte)-1));//1100001
//		System.out.println(Integer.toHexString(17));//11
		System.out.println(Md5Util.Md5("hello", "123", "456"));
		
//		StringBuffer buf = new StringBuffer("");
//		byte[] b = {-20, 41, 12, 123};
//		for (byte i : b) {
//			int a = i;
//			if (a<0) a += 256;
//			if (a<16) buf.append("0");
//			buf.append(Integer.toHexString(a));
//		}
//		System.out.println(buf.toString());
//		
//		StringBuffer buf1 = new StringBuffer("");
//		//byte[] b = {-20, 41, 124, 0};
//		for (byte i : b) {
//			int a = i & 0xFF;
//			//if (a<0) a += 256;
//			//if (a<16) buf1.append("0");
////			buf1.append(Integer.toHexString(a));
//			buf1.append(String.format("%02x", a));
//		}
//		System.out.println(buf1.toString());
//		
//		int t = 0xff;
	}
}
