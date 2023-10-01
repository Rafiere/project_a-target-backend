package com.atarget.atargetbackend.shared.utils;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public class StringUtils {

	/**
	 * <p>This method get the email local-part. If the email is "johndoe@email.com", it will return
	 * "johndoe".</p>
	 *
	 * @return the local-part of the email.
	 */
	public static String getEmailLocalPart(@Email final String email) {

		return email.split("@")[0];
	}

	public static String removeBearerFromToken(@NotBlank final String token){

		return token.replace("Bearer ", "");
	}
}
