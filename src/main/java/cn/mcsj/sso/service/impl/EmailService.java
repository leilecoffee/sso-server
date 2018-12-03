package cn.mcsj.sso.service.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cn.mcsj.sso.service.IEmailService;

/**
 * 邮件发送的实现类
 * 
 * @author admin
 *
 */
@Service
public class EmailService implements IEmailService {

	@Autowired
	private JavaMailSender mailSender; // 框架自带的

	@Value("${spring.mail.username}")
	private String from;
	@Value("${spring.mail.nick}")
	private String nick;

	@Async("mailAsync")
	public void sendSimpleMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		try {
			mailSender.send(message);
			System.out.println("简单邮件发送成功！");
		} catch (Exception e) {
			System.out.println("发送简单邮件时发生异常！" + e);
		}
	}

	@Async("mailAsync")
	public void sendHtmlMail(String to, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true); // true表示需要创建一个multipart message
			helper.setFrom(new InternetAddress(nick + " <" + from + ">"));
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			mailSender.send(message);
			System.out.println("html邮件发送成功");
		} catch (MessagingException e) {
			System.out.println("发送html邮件时发生异常！" + e);
		}
	}

	@Async("mailAsync")
	public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(new InternetAddress(nick + " <" + from + ">"));
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			helper.addAttachment(fileName, file);
			// helper.addAttachment("test"+fileName, file);

			mailSender.send(message);
			System.out.println("带附件的邮件已经发送。");
		} catch (MessagingException e) {
			System.out.println("发送带附件的邮件时发生异常！" + e);
		}
	}

}
