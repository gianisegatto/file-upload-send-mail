package com.gianisegatto.fileuploadmailsender.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gianisegatto.fileuploadmailsender.service.MailService;

@RestController
@RequestMapping(value = "mail-sender")
public class MailSenderController {

	private static final Logger LOG = LoggerFactory.getLogger(MailSenderController.class);
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value ="/send", method = RequestMethod.POST)
	public void sendMail(@RequestParam(value = "file", required = true) MultipartFile file) {
		
		try {
		
			List<String> lines = readFile(file);
		    
			for (String line : lines) {
				
				String[] lineSplit = line.split(";");

				String client = lineSplit[0];
				String email = lineSplit[1];
			
				String body = this.createBody(client);
			    
		    	this.mailService.sendMail(email, "Subject Test", body);
			}
			
		} catch (IOException e) {
			LOG.error("Error during send mail", e);
		}
		
	}

	private List<String> readFile(MultipartFile file) throws IOException {
		
		List<String> lines = new ArrayList<String>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
		br.lines().forEach(lines::add);
		br.close();
		
		return lines;
	}
	
	private String createBody(String client) {
		
		String body = String.format("E-mail sent for test in name of %s", client);
		
		return body;
	}
}