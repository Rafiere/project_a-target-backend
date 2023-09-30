package com.atarget.atargetbackend.shared.email;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

@Component
public class SendEmailWithAngusMailComponent {

	@Value("${spring.mail.host}") private String mailHost;
	@Value("${spring.mail.port}") private Integer mailPort;
	@Value("${spring.mail.username}") private String username;
	@Value("${spring.mail.password}") private String password;

	@Value("${spring.mail.protocol}") private String mailTransportProtocol;
	@Value("${spring.mail.properties.mail.smtp.auth}") private boolean smtpAuth;
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}") private boolean enableSsl;

	public void execute(final String emailSubject, final String emailTemplateToSend, final List<String> emailsToSendTo) {

		final JavaMailSenderImpl mailSender = configJavaMailSender();

		final List<MimeMessageHelper> mailMessages =
				generateMailMessages(mailSender, emailSubject, emailTemplateToSend, emailsToSendTo);

		sendMailMessages(mailSender, mailMessages);
	}

	private List<MimeMessageHelper> generateMailMessages(final JavaMailSenderImpl mailSender,
	                                               final String emailSubject,
	                                               final String emailTemplateToSend,
	                                               final List<String> emailsToSendTo) {

		return emailsToSendTo.stream()
		                     .map(emailToSendTo -> generateMailMessage(mailSender,
		                                                               emailSubject,
		                                                               emailTemplateToSend,
		                                                               emailToSendTo))
		                     .toList();
	}

	public MimeMessageHelper generateMailMessage(final JavaMailSenderImpl mailSender,
	                                       final String emailSubject,
	                                       final String emailTemplateToSend,
	                                       final String emailToSendTo) {

		try {

			final MimeMessage mailMessage = mailSender.createMimeMessage();
			final MimeMessageHelper emailHelper = new MimeMessageHelper(mailMessage);
			emailHelper.setFrom(username);
			emailHelper.setTo(emailToSendTo);
			emailHelper.setSubject(emailSubject);
			emailHelper.setText(emailTemplateToSend, true);
			return emailHelper;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private JavaMailSenderImpl configJavaMailSender() {

		final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(mailHost);
		mailSender.setPort(mailPort);
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		final Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", mailTransportProtocol);
		props.put("mail.smtp.auth", smtpAuth);
		props.put("mail.smtp.starttls.enable", enableSsl);

		return mailSender;
	}

	private void sendMailMessages(final JavaMailSenderImpl mailSender, final List<MimeMessageHelper> mailMessages) {

		mailMessages.forEach(mailMessage -> {
			try {
				mailSender.send(mailMessage.getMimeMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
