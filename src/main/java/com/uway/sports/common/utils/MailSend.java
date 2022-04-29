package com.uway.sports.common.utils;


import com.uway.sports.common.config.Global;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MailSend {

	private String uwayUserName = Global.getConfig("uway.mail.userName");
	private String uwayPassword = Global.getConfig("uway.mail.password");
	private String uwaySmtp = Global.getConfig("uway.smtp");
	
	public MailSend(String recipient, String subject, String content){
		this.recipient = recipient;
		this.subject = subject;
		this.content = content;
	}

	public MailSend(List<String> recipients, String subject, String content){
		this.recipients = recipients;
		this.subject = subject;
		this.content = content;
	}

	public MailSend(String recipient, String subject, String content,List<File> fileList){
		this.recipient = recipient;
		this.subject = subject;
		this.content = content;
		this.fileList = fileList;
	}

	public MailSend(List<String> recipients, String subject, String content,List<File> fileList){
		this.recipients = recipients;
		this.subject = subject;
		this.content = content;
		this.fileList = fileList;
	}

	private String subject;
	private String content;
	private String recipient;
	private List<String> recipients;
	private List<File> fileList;

	public List<File> getFileList() {
		return fileList;
	}

	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}
	
	public void sendMail() throws AddressException, MessagingException {
		MailSender sender = new MailSender(decodeBase64(this.uwayUserName), decodeBase64(this.uwayPassword), decodeBase64(this.uwaySmtp));
		sender.send(this);
	}

	public void sendMulti() throws AddressException, MessagingException {
		MailSender sender = new MailSender(decodeBase64(this.uwayUserName), decodeBase64(this.uwayPassword), decodeBase64(this.uwaySmtp));
		sender.sendMulti(this);
	}

	@SuppressWarnings("restriction")
	public static String decodeBase64(String str){    
		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(bt);
	}
}
