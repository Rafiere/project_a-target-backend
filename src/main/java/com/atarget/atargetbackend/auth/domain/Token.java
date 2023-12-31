package com.atarget.atargetbackend.auth.domain;

import com.atarget.atargetbackend.auth.domain.enums.TokenStatus;
import com.atarget.atargetbackend.auth.domain.enums.TokenType;
import com.atarget.atargetbackend.shared.audit.BaseAuditableEntity;
import com.atarget.atargetbackend.shared.exception.custom.BusinessRuleException;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Entity
public class Token extends BaseAuditableEntity {

	@Id @Getter private String id;
	@Getter private String tokenText;
	@Getter private String email;
	@Enumerated(EnumType.STRING) private TokenType tokenType;
	@Enumerated(EnumType.STRING) private TokenStatus tokenStatus;
	private LocalDateTime expirationDate;
	private Character charToConcatenateWithToken;

	public Token() {

	}

	private Token(final String tokenText,
	              final Character charToConcatenateWithToken,
	              final String email,
	              final LocalDateTime expirationDate,
	              final TokenType tokenType) {

		this.id = UUID.randomUUID()
		              .toString();
		this.tokenText = tokenText;
		this.charToConcatenateWithToken = charToConcatenateWithToken;
		this.email = email;
		this.expirationDate = expirationDate;
		this.tokenType = tokenType;
		this.tokenStatus = TokenStatus.CREATED;
	}

	public static Token of(final String email,
	                       final Integer expirationTimeInSeconds,
	                       final TokenType tokenType,
	                       final Integer tokenLength,
	                       final Character charToConcatenateWithToken,
	                       final String concatenateToToken) {

		final String tokenText = generateTokenText(tokenLength, charToConcatenateWithToken, concatenateToToken);
		final LocalDateTime expirationDate = LocalDateTime.now()
		                                                  .plusSeconds(expirationTimeInSeconds);

		return new Token(tokenText, charToConcatenateWithToken, email, expirationDate, tokenType);
	}

	public Token validate() {

		if (this.tokenStatus.equals(TokenStatus.USED)) {
			throw BusinessRuleException.of("The token " +
			                               this.tokenText +
			                               " is already used.");
		}

		if (this.expirationDate.isBefore(LocalDateTime.now()) ||
		    this.expirationDate.isEqual(LocalDateTime.now())) {
			throw BusinessRuleException.of("The token " +
			                               this.tokenText +
			                               " is expired.");
		}

		return this;
	}

	/**
	 * <p>This method will generate a token text with a specified length.</p>
	 *
	 * @param tokenLength                the length of the token text
	 * @param concatenateToToken         the text to concatenate with token text
	 * @param charToConcatenateWithToken the character to indicate the start of the concatenation between token text and {@code concatenateToToken}
	 * @return the generated token text
	 */
	private static String generateTokenText(final Integer tokenLength,
	                                        final Character charToConcatenateWithToken,
	                                        final String concatenateToToken) {

		final String POSSIBLE_CHARACTERS = "ABCDEFGHIJKLNOPQRSTUVXYZ1234567890";
		final SecureRandom secureRandom = new SecureRandom();

		final String tokenText = IntStream.range(0, tokenLength)
		                            .mapToObj(i -> POSSIBLE_CHARACTERS.charAt(secureRandom.nextInt(POSSIBLE_CHARACTERS.length())))
		                            .map(Objects::toString)
		                            .collect(Collectors.joining());

		return concatenateToToken ==
		       null ? tokenText : tokenText.concat(charToConcatenateWithToken.toString())
		                                   .concat(concatenateToToken);
	}

	public String getTokenConcatenatedPart() {

		return this.tokenText.split(this.charToConcatenateWithToken.toString())[1];
	}

	public Token markAsSent() {

		this.tokenStatus = TokenStatus.SENT;

		return this;
	}

	public Token useToken() {

		this.tokenStatus = TokenStatus.USED;

		return this;
	}

	public String getTokenUserId() {

		if (!this.tokenType.equals(TokenType.ACTIVATE_ACCOUNT)) {
			return "";
		}

		return this.tokenText.split(this.charToConcatenateWithToken.toString())[1];
	}

	public void validateGivenEmailWithTokenEmail(final String givenEmail) {

		if (!givenEmail.equals(this.email)) {
			throw new IllegalArgumentException("The email " +
			                                   givenEmail +
			                                   " does not match with the token email.");
		}
	}

	public String generateTokenLink(final String appUrl, final String tokenLink) {

		if (!(this.tokenType.equals(TokenType.ACTIVATE_ACCOUNT) || this.tokenType.equals(TokenType.PASSWORD_RECOVERY))) {

			return "";
		}

		return appUrl +
		       tokenLink +
		       this.tokenText;
	}
}
