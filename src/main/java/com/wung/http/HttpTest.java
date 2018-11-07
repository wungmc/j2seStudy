package com.wung.http;


import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 *
 * Created by wung on 2016/7/22.
 */
public class HttpTest {
    public static void main(String[] args) throws Exception {
//        HttpTest.getDemo("http://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=15850781443");
        HttpTest.postDemo();
    }

    public static String getDemo(String url) throws Exception {
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        HttpMethodParams httpMethodParams = getMethod.getParams();
        System.out.println(httpMethodParams.getContentCharset());
        System.out.println(httpMethodParams.getHttpElementCharset());
        System.out.println(httpMethodParams.getSoTimeout());
        System.out.println(httpMethodParams.getUriCharset());
        System.out.println(httpMethodParams.getVersion());

        //设置成了默认的恢复策略，在发生异常时候将自动重试3次
        httpMethodParams.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        //执行getMethod
        int statusCode = httpClient.executeMethod(getMethod);
        System.out.println("statusCode = " + statusCode);
        if (statusCode != HttpStatus.SC_OK) {
            System.out.println(getMethod.getStatusLine());
        }
//        byte[] responseBody = getMethod.getResponseBody();
//        getMethod.releaseConnection();
//        System.out.println(new String(responseBody, "gbk"));

        System.out.println(getMethod.getResponseBodyAsString());

        return null;
    }

    public static String postDemo() throws Exception {
        String url = "http://127.0.0.1:8082/backyard/login.jsp";

        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        NameValuePair[] paras = {
                new NameValuePair("username", "admin"),
                new NameValuePair("password", "123456")
        };
        postMethod.setRequestBody(paras);

        int statusCode = httpClient.executeMethod(postMethod);
        if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
            Header header = postMethod.getResponseHeader("location");
            if (header != null) {
                System.out.println(header.getValue());
            }
        }
        if (statusCode == HttpStatus.SC_OK) {
            System.out.println(postMethod.getResponseBodyAsString());
        }

        return null;
    }
}
