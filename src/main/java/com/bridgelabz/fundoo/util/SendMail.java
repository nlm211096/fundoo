package com.bridgelabz.fundoo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.model.Mail;



@Component
public class SendMail {
      
	@Autowired
    private JavaMailSender emailSender;

  //  private static Logger log = LoggerFactory.getLogger(Application.class);

//
//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		//log.info("Spring Mail - Sending Simple Email with JavaMailSender Example");
//
//        Mail mail = new Mail();
//        mail.setFrom("no-reply@memorynotfound.com");
//        mail.setTo("info@memorynotfound.com");
//        mail.setSubject("Sending Simple Email with JavaMailSender Example");
//        mail.setContent("This tutorial demonstrates how to send a simple email using Spring Framework.");
//
//        sendSimpleMessage(mail);
//		
//	}
//	
	public void sendSimpleMessage( Mail mail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        message.setTo(mail.getTo());
        message.setFrom(mail.getFrom());

        emailSender.send(message);
    }

}
