/**
 *
 */
package com.wung.util;


import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;

import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class EmailUtil {

    static Logger logger = Logger.getLogger(EmailUtil.class);

    /**
     * 发送邮件
     *
     * @param emailBo 邮件实体信息
     * @param batchSend 是否批量发，true：一次性发送； false：循环一个一个的发送
     * @throws UnsupportedEncodingException
     * @throws EmailException
     */
    public static void sendEmail (EmailBo emailBo, boolean batchSend) throws UnsupportedEncodingException, EmailException {
        if (emailBo == null) {
            logger.info("邮件信息为 null，停止发送！");
            return;
        }

        if (batchSend) {
            EmailUtil.sendEmail(emailBo);
            return;
        }


        EmailAttachment attachment = null;
        //添加附件
        if (emailBo.getAttachmentPath() != null) {
            attachment = new EmailAttachment();
            attachment.setPath(emailBo.getAttachmentPath());
            if (emailBo.getAttachmentName() != null) {
                //附件名称需要进行编码,防止中文乱码
                attachment.setName(MimeUtility.encodeText(emailBo.getAttachmentName()));
            }

            attachment.setDisposition(EmailAttachment.ATTACHMENT);
        }

        Set<String> addresss = new HashSet<String>();
        if(emailBo.getToList() != null) {
            Collections.addAll(addresss, emailBo.getToList());
        }
        if(emailBo.getCcList() != null) {
            Collections.addAll(addresss, emailBo.getCcList());
        }

        logger.info("邮件准备发送...");

        Iterator<String> iter = addresss.iterator();
        while (iter.hasNext()) {
            String address = iter.next();
            try {
                MultiPartEmail mail = new MultiPartEmail();
                if (attachment != null) {
                    mail.attach(attachment);
                }
                mail.setHostName("localhost");
                mail.setAuthentication(emailBo.getFrom(), emailBo.getPassword());
                mail.setFrom(emailBo.getFrom(), emailBo.getFromName());
                mail.setSubject(emailBo.getSubject());
                mail.setMsg(emailBo.getContent());
                mail.addTo(address);
                mail.send();
            } catch (Exception e) {
                logger.error("发送给" + address + "时失败：" + e.getMessage());
            }
        }
        logger.info("邮件发送完毕！");
    }

    /**
     * 批量发送给多个人（如果其中一个失败，则全部失败）
     *
     * @param emailBo
     * @throws UnsupportedEncodingException
     * @throws EmailException
     */
    private static void sendEmail (EmailBo emailBo) throws UnsupportedEncodingException, EmailException {
        if (emailBo == null) {
            logger.info("邮件信息为 null，停止发送！");
            return;
        }

        MultiPartEmail mail = new MultiPartEmail();
        EmailAttachment attachment = new EmailAttachment();
        //添加附件
        if (emailBo.getAttachmentPath() != null) {
            attachment.setPath(emailBo.getAttachmentPath());
            if (emailBo.getAttachmentName() != null) {
                //附件名称需要进行编码,防止中文乱码
                attachment.setName(MimeUtility.encodeText(emailBo.getAttachmentName()));
            }

            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            mail.attach(attachment);
        }

        mail.setHostName("localhost");
        mail.setAuthentication(emailBo.getFrom(), emailBo.getPassword());

        if(emailBo.getToList() != null) {
            for (String s : emailBo.getToList()) {
                mail.addTo(s);
            }
        }
        if(emailBo.getCcList() != null) {
            for (String s : emailBo.getCcList()) {
                mail.addCc(s);
            }
        }

        mail.setFrom(emailBo.getFrom(), emailBo.getFromName());
        mail.setSubject(emailBo.getSubject());
        mail.setMsg(emailBo.getContent());
        logger.info("邮件准备发送...");
        mail.send();
        logger.info("邮件发送完毕！");
    }

	public static void main(String[] args) {

	}
}
