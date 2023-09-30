package com.atarget.atargetbackend.shared.email;

import com.atarget.atargetbackend.auth.domain.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GenerateEmailComponent {

	private final SpringTemplateEngine templateEngine;

	@Value("${environment.api.base-url}") String apiBaseUrl;
	@Value("${environment.api.port}") String apiPort;
	@Value("${environment.front-end.url}") String frontendUrl;


	public String generateActivateAccountEmailTemplate(final Token token) {

		final Context thymeleafContext = new Context(new Locale("br"));

		final Map<String, Object> activateAccountEmailVariables = new HashMap<>(Map.of(
				"name", token.getTokenUserId(),
				"link", token.generateTokenLink(apiBaseUrl + apiPort, "/auth/activate-account/")
		));

		thymeleafContext.setVariables(activateAccountEmailVariables);

		final String activateAccountEmailTemplateFileName = "activate-account-email-template.html";
		return templateEngine.process(activateAccountEmailTemplateFileName, thymeleafContext);
	}

	public String generateRecoveryPasswordEmailTemplate(final Token token, final String personaNickname) {

		final Context thymeleafContext = new Context(new Locale("br"));

		final Map<String, Object> recoveryPasswordEmailVariables = new HashMap<>(Map.of(
				"name", personaNickname,
				"link", token.generateTokenLink(frontendUrl, "/auth/recovery-password/")
		));

		thymeleafContext.setVariables(recoveryPasswordEmailVariables);

		final String activateAccountEmailTemplateFileName = "recovery-password-email-template.html";
		return templateEngine.process(activateAccountEmailTemplateFileName, thymeleafContext);
	}
}
