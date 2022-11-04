package com.niit.EmailService.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

@Service
public class EmailService {

    public boolean sendEmail(String to, String subject, String message){
        boolean f=false;
        String from="officialtodotracker@gmail.com";
        String password="tznqkivsimotrxes";
        String host="smtp.gmail.com";

        Properties properties=System.getProperties();
        System.out.println("Properties"+properties);

        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from,password);
            }
        });
        session.setDebug(true);

        MimeMessage m=new MimeMessage(session);

        try{
            m.setFrom(from);
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(subject);
            m.setText(message);

            Transport.send(m);
            System.out.println("Sent Success.....");
            f=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return f;
    }


}
