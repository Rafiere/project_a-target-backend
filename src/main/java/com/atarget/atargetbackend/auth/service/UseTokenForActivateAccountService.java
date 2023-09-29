package com.atarget.atargetbackend.auth.service;

import com.atarget.atargetbackend.auth.domain.Token;
import com.atarget.atargetbackend.auth.domain.User;
import com.atarget.atargetbackend.auth.repository.TokenRepository;
import com.atarget.atargetbackend.persona.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UseTokenForActivateAccountService {

	@Value("${environment.api.base-url}") String apiBaseUrl;
	private final TokenRepository tokenRepository;
	private final UserRepository userRepository;

	public void execute(String token) {

		Token foundToken = tokenRepository.findTokenByTokenText(token)
		                             .orElseThrow(() -> new IllegalArgumentException("The token " + token + " does not exist."));

		String tokenOwnerUserEmail = foundToken.getEmail();

		User user = userRepository.findUserByEmail(tokenOwnerUserEmail)
		                          .orElseThrow(() -> new IllegalArgumentException("An user with specified token email does not exist."));

		foundToken.validate().useToken();
		user.activateAccount();
	}
}
