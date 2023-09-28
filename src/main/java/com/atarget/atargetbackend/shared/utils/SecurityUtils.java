package com.atarget.atargetbackend.shared.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtils {

	public static String encryptAPassword(String password){

		var bcrypt = new BCryptPasswordEncoder();

		return bcrypt.encode(password);
	}
}
