package com.wung.dynamicproxy;

/**
 *
 * Created by wung on 2016/5/11.
 */
public class BookingTicket implements SellTicket {
    @Override
    public void sellTicket() {
        System.out.println("sell a ticket.");
    }
}
