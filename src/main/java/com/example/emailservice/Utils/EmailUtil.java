package com.example.emailservice.Utils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public class EmailUtil {
    public static void sendEmail(String to, String subject, String content, Session session) {
        try{
            MimeMessage message = new MimeMessage(session);
            //set message headers
            message.addHeader("Content-type","text/HTML; charset = UTF-8");
            message.addHeader("format","flowed");
            message.addHeader("Content-Transfer-Encoding","8bit");

            message.setFrom(new InternetAddress("dhanushdh01@gmail.com","NoReply-JD"));
            message.setReplyTo(InternetAddress.parse("dhanushdh01@gmail.com",false));
            message.setSubject(subject,"UTF-8");
            message.setText(content,"UTF-8");
            message.setSentDate(new Date());
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to,false));
            System.out.println("Email is ready to send...");
            Transport.send(message);
            System.out.println("Email is sent Successfully to the : " + to);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
