package com.bridgelabz.fundoo.util;

import java.util.Properties;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;


@Configuration
@PropertySource("classpath:info.properties")
public class MailServiceProvider {

	@Autowired
	private static JavaMailSender javaMailSender;
	// private static JavaMailSender javaMailSender = new JavaMailSenderImpl();
	////////Info related Msg
	@Value("${status}" )
	String status;
	
	@Value("${fromEmail}" )
	String fromEmail ;
	
	@Value("${password}" )
	String password;
	
	@Value("${auth}" )
	String auth;
	
	@Value("${mail.smtp.starttls.enable}")
	String mail_smtp_starttls_enable;
	
	@Value("${host}" )
	String host;
	@Value("${smtp.gmail.com}" )
	String smtp_gmail;
	
	@Value("${port}" )
	String port;
	
	@Value("${portNumber}" )
	String portNumber;
	

	public MailServiceProvider() {

	}

	public void sendEmail(String toEmail, String subject, String body) {
        
		
		
//		String fromEmail = "bridgelab123@gmail.com";
//		String password = "Bridgelab@123";

		Properties prop = new Properties();
		//prop.put("mail.smtp.auth", "true");
		prop.put(auth, status);
		prop.put(mail_smtp_starttls_enable, status);
		prop.put(host, smtp_gmail);
		prop.put(port, portNumber);

		Authenticator auth = new Authenticator()
		{

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(prop, auth);
		send(session, fromEmail, toEmail, subject, body);
	}

	private void send(Session session, String fromEmail, String toEmail, String subject, String body) {
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail, "neelam"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
			// javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception occured while sending mail");

		}
	}

}
