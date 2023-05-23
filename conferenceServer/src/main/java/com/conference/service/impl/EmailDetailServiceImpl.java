package com.conference.service.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.conference.entity.email.EmailDetail;
import com.conference.service.EmailDetailService;

@Service
public class EmailDetailServiceImpl implements EmailDetailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}") private String sender;

	@Override
	public String sendSimpleEmail( EmailDetail emailDetail ) {
		
		try {
			
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			
			simpleMailMessage.setFrom(sender);
			simpleMailMessage.setTo( emailDetail.getRecipient() );
			simpleMailMessage.setText( emailDetail.getMsgBody() );
			simpleMailMessage.setSubject( emailDetail.getSubject());
			
			javaMailSender.send(simpleMailMessage);
			return "Email sent successfully!";
		}
		catch( Exception e ) {
			e.printStackTrace();
			return "Error while sending Email...";
		}
	}

	@Override
	public String sendMailWithAttachment( EmailDetail emailDetail ) {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper mimeMessageHelper;
		
		try {
			mimeMessageHelper = new MimeMessageHelper( mimeMessage, true );
			
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo( emailDetail.getRecipient() );
			mimeMessageHelper.setText( emailDetail.getMsgBody() );
			mimeMessageHelper.setSubject( emailDetail.getSubject() );
			
			FileSystemResource file = new FileSystemResource( new File( emailDetail.getAttachment() ) );
			
			mimeMessageHelper.addAttachment( file.getFilename(), file);
			
			javaMailSender.send(mimeMessage);
			return "Email sent successfully!";
			
		}
		catch( MessagingException e ) {
			e.printStackTrace();
			return "Error while sending Email...";
		}
	}

}
