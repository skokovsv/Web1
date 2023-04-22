package com.skokov.start.domain.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.mail.*;
import java.util.Date;
import java.util.Properties;

@EnableAsync
@Service
public class myMailSender {
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private String port;

    @Async
    public void send(String emailTo,String subject,String text){
        System.setProperty("javax.net.ssl.trustStore","NUL");
        System.setProperty("javax.net.ssl.trustStoreType","Windows-ROOT");

        Properties prop = new Properties();
        prop.put("mail.smtp.host",host);
        prop.put("mail.smtp.port",port);
        prop.put("mail.smtp.ssl.enable","true");
        prop.put("mail.smtp.auth","true");
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });

        System.out.println("MySender::send: OK host="+ host +" port=" + " username=" + username +" password=" + password);

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            InternetAddress[] addresses = {new InternetAddress(emailTo)};
            message.setRecipients(Message.RecipientType.TO,addresses);
            message.setSentDate(new Date());
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        }

    }

}
