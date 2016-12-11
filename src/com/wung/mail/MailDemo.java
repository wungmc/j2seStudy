package com.wung.mail;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * Created by wung on 2016/12/11.
 */
public class MailDemo {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        //邮件体
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("wungcc@qq.com"));
        msg.setSubject("ThreadQ1");
        msg.setText("ThreadQ1 java mail！");

        //发送
        Transport tran = session.getTransport();
        tran.connect("smtp.qq.com", 25, "username", "password");
        //send()是个静态方法，里面包含了连接，发送和关闭三步
        //tran.send(msg, new Address[]{new InternetAddress("wungcc@163.com")});
        tran.sendMessage(msg, new Address[]{new InternetAddress("wungcc@gmail.com")});
        tran.close();
    }
}
