package com.atarget.atargetbackend.persona.domain;

import com.atarget.atargetbackend.shared.audit.BaseAuditableEntity;
import com.atarget.atargetbackend.shared.utils.StringUtils;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Getter;

import java.security.SecureRandom;
import java.util.UUID;

@Entity
public class PersonalData extends BaseAuditableEntity {

	@Getter @Id private String id;
	@Getter private String nickname;
	@Getter @Embedded private Avatar avatar;

	public PersonalData(){}

	private PersonalData(final String nickname) {
		this.id = UUID.randomUUID().toString();
		this.nickname = nickname;
	}

	public static PersonalData of(final String nickname) {
		return new PersonalData(nickname);
	}

	/**
	 * <p>All of the new accounts must have a system-generated nickname. This method will generate it.</p>
	 *
	 * @param email the email of the user.
	 * @return the account first nickname
	 */
	public static String generateFirstAccountNickname(final @Email String email) {

		var stringBuilder = new StringBuilder();

		return stringBuilder.append(StringUtils.getEmailLocalPart(email))
		                    .append(generateARandomNumberForFirstAccountName())
		                    .toString();
	}

	private static Integer generateARandomNumberForFirstAccountName(){

		final int MINIMUM_NUMBER = 0;
		final int MAXIMUM_NUMBER = 500000;

		var secureRandom = new SecureRandom();

		return secureRandom.nextInt(MINIMUM_NUMBER, MAXIMUM_NUMBER);
	}
}
