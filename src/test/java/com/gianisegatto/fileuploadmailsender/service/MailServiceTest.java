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
	public void testSendMail() throws AddressException {
		
		List<String> toList = new ArrayList<String>();
		toList.add("gianisegatto@gmail.com");
		toList.add("renatakazuenunes@gmail.com");
		
		String subject = "E-mail test";
		String body = "Testing send mail";
		
		this.mailService.sendMail(toList, subject, body);
	}
	
}
