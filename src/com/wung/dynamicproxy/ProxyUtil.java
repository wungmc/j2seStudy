package com.wung.dynamicproxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * Created by wung on 2016/5/12.
 */
public class ProxyUtil {
    /**
     * Save proxy class to path
     *
     * @param path path to save proxy class
     * @param proxyClassName name of proxy class
     * @param interfaces interfaces of proxy class
     * @return
     */
    public static boolean saveProxyClass(String path, String proxyClassName, Class[] interfaces) {
        if (proxyClassName == null || path == null) {
            return false;
        }

        // get byte of proxy class
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyClassName, interfaces);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ProxyUtil.saveProxyClass("D:\\$Proxy0.class", "$Proxy0", new Class[]{SellTicket.class});
    }
}
