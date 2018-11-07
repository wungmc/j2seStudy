package com.wung.util;

import java.util.regex.Pattern;


/**
 * 
 * @author wung
 *
 */
public class StringUtil {
	
	//yyyy-mm-dd 格式的日期
	public static final String DATE_YYYY_MM_DD = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
	//0或负整数
	public static final String ZERO_NEGATIVE_INT = "^((-\\d+)|(0+))$";
	//正整数
	public static final String POSITIVE_INT= "^[1-9]\\d*$";
	
	
	public static boolean match(String pattern, String str) {
		if (str == null) {
			return false;
		}
		
		return Pattern.compile(pattern).matcher(str).matches();
	}
	
	/**
	 * 校验字符串是否是 yyyy-mm-dd 格式的日期字符串
	 * @param date
	 * @return
	 */
	public static boolean isDate_YYYY_MM_DD(String date) {
		return StringUtil.match(StringUtil.DATE_YYYY_MM_DD, date);
	}
	
	/**
	 * 校验字符串是否是0或负整数
	 * @param str
	 * @return
	 */
	public static boolean isZERO_NEGATIVE_INT(String str) {
		return StringUtil.match(StringUtil.ZERO_NEGATIVE_INT, str);
	}

	/**
	 * 校验字符串是否是正整数
	 * @param str
	 * @return
	 */
	public static boolean isPOSITIVE_INT(String str) {
		return StringUtil.match(StringUtil.POSITIVE_INT, str);
	}
	
	/**
	 * 校验字符串是否是整数
	 * @param str
	 * @return
	 */
	public static boolean isINT(String str){
		return StringUtil.match(StringUtil.POSITIVE_INT, str) || StringUtil.match(StringUtil.ZERO_NEGATIVE_INT, str);
	}
	
	public static void main(String[] args) {
		System.out.println(StringUtil.isDate_YYYY_MM_DD("2015-02-31"));
		System.out.println(StringUtil.isZERO_NEGATIVE_INT("0"));
		System.out.println(StringUtil.isPOSITIVE_INT("01"));
	}

}
