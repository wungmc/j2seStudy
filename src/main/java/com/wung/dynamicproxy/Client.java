package com.wung.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 *
 * Created by wung on 2016/5/11.
 */
public class Client {
    public static void main(String[] args) {
        SellTicket sellTicket = (SellTicket) Proxy.newProxyInstance(SellTicket.class.getClassLoader(), new Class[]{SellTicket.class},
                new InvocationHandlerImpl(new BookingTicket()));
        sellTicket.sellTicket();

        System.out.println(sellTicket.getClass());
    }
}

// out
// before........
// sell a ticket.
// after........
// class com.sun.proxy.$Proxy0
