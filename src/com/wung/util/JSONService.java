package com.wung.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * 将对象转换成 json 格式，并写入 response 中。
 */
public class JSONService {
	
	public JSONService(){
		
	}

	//List
	public static String listToJson(List<?> list){
		return JSONArray.fromObject(list).toString();
	}
	//Map,普通类
	public static String beanToJSON(Object bean){
		return JSONObject.fromObject(bean).toString();
	}


	//将未转换的bean(map,普通类)放入HttpServletResponse内
	public static void writeBeanIntoResponse(HttpServletResponse response,Object bean) {
	    writeJsonIntoResponse(response,beanToJSON(bean));
	}

	//将未转换的list放入HttpServletResponse内
	public static void  writeListIntoResponse(HttpServletResponse response,List<?> list) {
	    writeJsonIntoResponse(response,listToJson(list));
	}
	
    public static void writeStringIntoResponse(HttpServletResponse response, String str) {
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html; charset=UTF-8");
        writeString2Response(response, str);
    }

    //将转换好的json数据放入HttpServletResponse内
    private static void writeJsonIntoResponse(HttpServletResponse response, String json) {
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/json; charset=UTF-8");
        writeString2Response(response, json);
    }


	//将未转换的bean(map,普通类)放入HttpServletResponse内 按照指定编码
	public static void writeBeanIntoResponse(HttpServletResponse response,Object bean, String charset) {
		writeJsonIntoResponse(response,beanToJSON(bean), charset);
	}

	//将未转换的list放入HttpServletResponse内 按照指定编码
	public static void  writeListIntoResponse(HttpServletResponse response,List<?> list, String charset) {
		writeJsonIntoResponse(response,listToJson(list), charset);
	}

    public static void writeStringIntoResponse(HttpServletResponse response, String string, String charset) {
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html; charset=" + charset);
        writeString2Response(response, string);
    }

    //将转换好的json数据放入HttpServletResponse内  按照指定编码
    public static void writeJsonIntoResponse(HttpServletResponse response, String json, String charset) {
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/json; charset=" + charset);
        writeString2Response(response, json);
    }

    private static void writeString2Response(HttpServletResponse response, String str) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(str);
            out.flush();
        } catch (Exception e) {
            out.write("error");
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
    }

}
