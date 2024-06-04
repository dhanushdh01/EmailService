package com.example.emailservice.Service;

import com.example.emailservice.DTOs.SendEmailDTO;
import com.example.emailservice.Utils.EmailUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.Session;
import java.util.Properties;

@Service
public class SendEmailService {
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "sendEmail",groupId = "emailService")
    public void listen(String message) {
        try{
            SendEmailDTO sendEmailDTO = objectMapper.readValue(message, SendEmailDTO.class);
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.starttls.enable", "true");

            //Create Authenticator objet to pass in Session
            Authenticator authenticator = new Authenticator() {
                //Override the getPasswordAuthenticator method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("dhanushdh01@gmail.com", "********");
                }
            };
            Session session = Session.getInstance(props,authenticator);
            EmailUtil.sendEmail(sendEmailDTO.getTo(),sendEmailDTO.getSubject(),sendEmailDTO.getBody(),session);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
