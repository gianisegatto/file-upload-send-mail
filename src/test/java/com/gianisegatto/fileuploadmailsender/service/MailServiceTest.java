package com.gianisegatto.fileuploadmailsender.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gianisegatto.fileuploadmailsender.AbstractTest;


public class MailServiceTest extends AbstractTest {

	@Autowired
	private MailService mailService;
	
	@Test
	public void shouldSendListOfMail() throws AddressException {
		
		List<String> toList = new ArrayList<String>();
		toList.add("test@gmail.com");
		toList.add("test2@gmail.com");
		
		String subject = "E-mail test";
		String body = "Testing send mail";
		
		this.mailService.sendMail(toList, subject, body);
	}
	
	
	@Test
	public void shouldSendOneOfMail() throws AddressException {
		
		this.mailService.sendMail("test@gmail.com", "E-mail test", "Testing send mail");
	}
	
}
