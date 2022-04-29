package com.uway.sports.common.utils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

public class MailSender {

	private final transient Properties props = System.getProperties();
	private transient MailAuthenticator authenticator;
	private transient Session session;

	public MailSender(final String username,
					  final String password, final String smtpHostName) {
		init(username, password, smtpHostName);
	}

	private void init(String username, String password, String smtpHostName) {
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", smtpHostName);
		authenticator = new MailAuthenticator(username, password);
		session = Session.getInstance(props, authenticator);
	}

	public void send(String recipient, String subject, Object content)
			throws AddressException, MessagingException {
		final MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(authenticator.getUserName()));
		message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
		message.setSubject(subject);
		message.setContent(content.toString(), "text/html;charset=utf-8");
		Transport.send(message);
	}

	public void send(List<String> recipients, String subject, Object content)
			throws AddressException, MessagingException {
		final MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(authenticator.getUserName()));
		final int num = recipients.size();
		InternetAddress[] addresses = new InternetAddress[num];
		for (int i = 0; i < num; i++) {
			addresses[i] = new InternetAddress(recipients.get(i));
		}
		message.setRecipients(RecipientType.TO, addresses);
		message.setSubject(subject);
		message.setContent(content.toString(), "text/html;charset=utf-8");
		Transport.send(message);
	}

	public void send(String recipient, String subject, Object content, List<File> fileList)
			throws AddressException, MessagingException {
		final MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(authenticator.getUserName()));
		message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
		message.setSubject(subject);
		MimeMultipart bodyPart = new MimeMultipart("mixed");
		MimeBodyPart contentBody = new MimeBodyPart();
		contentBody.setContent(content,"text/html;charset=utf-8");
		bodyPart.addBodyPart(contentBody);
		if(fileList != null && fileList.size() > 0){
			for(File file : fileList){
				MimeBodyPart attch = new MimeBodyPart();
				try {
					attch.setFileName(MimeUtility.encodeText(file.getName()));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				DataSource dataSource = new FileDataSource(file);
				attch.setDataHandler(new DataHandler(dataSource));
				bodyPart.addBodyPart(attch);
			}
		}
		message.setContent(bodyPart);
		Transport.send(message);
	}

	public void send(List<String> recipients, String subject, Object content, List<File> fileList)
			throws AddressException, MessagingException {
		final MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(authenticator.getUserName()));
		final int num = recipients.size();
		InternetAddress[] addresses = new InternetAddress[num];
		for (int i = 0; i < num; i++) {
			addresses[i] = new InternetAddress(recipients.get(i));
		}
		message.setRecipients(RecipientType.TO, addresses);
		message.setSubject(subject);
		MimeMultipart bodyPart = new MimeMultipart("mixed");
		MimeBodyPart contentBody = new MimeBodyPart();
		contentBody.setContent(content,"text/html;charset=utf-8");
		bodyPart.addBodyPart(contentBody);
		if(fileList != null && fileList.size() > 0){
			for(File file : fileList){
				MimeBodyPart attch = new MimeBodyPart();
				try {
					attch.setFileName(MimeUtility.encodeText(file.getName()));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				DataSource dataSource = new FileDataSource(file);
				attch.setDataHandler(new DataHandler(dataSource));
				bodyPart.addBodyPart(attch);
			}
		}
		message.setContent(bodyPart);
		Transport.send(message);
	}

	public void send(MailSend mail) throws AddressException,
            MessagingException {
		if(mail.getFileList() != null){
			send(mail.getRecipient(), mail.getSubject(), mail.getContent(),mail.getFileList());
		}else{
			send(mail.getRecipient(), mail.getSubject(), mail.getContent());
		}
	}

	public void sendMulti(MailSend mail)
			throws AddressException, MessagingException {
		if(mail.getFileList() != null){
			send(mail.getRecipients(), mail.getSubject(), mail.getContent(),mail.getFileList());
		}else{
			send(mail.getRecipients(), mail.getSubject(), mail.getContent());
		}
	}

}
