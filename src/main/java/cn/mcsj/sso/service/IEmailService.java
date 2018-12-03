package cn.mcsj.sso.service;

public interface IEmailService {

	void sendSimpleMail(String to, String subject, String content);

	void sendHtmlMail(String to, String subject, String content);

	void sendAttachmentsMail(String to, String subject, String content, String filePath);
}
