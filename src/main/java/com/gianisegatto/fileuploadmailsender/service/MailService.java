package com.gianisegatto.fileuploadmailsender.service;

import java.util.List;

public interface MailService {

	public void sendMail(String to, String subject, String body);

	public void sendMail(List<String> toList, String subject, String body);
}
