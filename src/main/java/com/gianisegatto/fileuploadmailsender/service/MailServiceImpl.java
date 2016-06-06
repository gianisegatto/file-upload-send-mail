package com.gianisegatto.fileuploadmailsender.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component(value="mailService")
public class MailServiceImpl implements MailService {

	private static final Logger LOG = LoggerFactory.getLogger(MailServiceImpl.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendMail(List<String> toList, String subject, String body) {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
		
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
		
			messageHelper.setSubject(subject);
			messageHelper.setTo(toList.toArray(new String[toList.size()]));
			messageHelper.setText(body);
	
			mailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			LOG.error("Error during send mail", e);
		}
	}

	@Override
	public void sendMail(String to, String subject, String body) {
		List<String> toList = new ArrayList<>(1);
		toList.add(to);
		
		this.sendMail(toList, subject, body);
	}
}