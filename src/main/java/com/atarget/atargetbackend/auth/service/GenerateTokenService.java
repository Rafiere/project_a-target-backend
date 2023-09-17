package com.atarget.atargetbackend.auth.service;

import com.atarget.atargetbackend.auth.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class GenerateTokenService {

	@Value("${environment.api.security.token.secret}") private String secret;
	@Value("${environment.api.security.token.access-token.expiration-in-seconds}") private Long accessTokenExpirationInSeconds;

	public String generateToken(User user) {

		try {

			Algorithm algorithm = Algorithm.HMAC256(secret);

			return JWT.create()
			                  .withIssuer("a-target")
			                  .withSubject(user.getEmail())
			                  .withExpiresAt(generateExpirationDate())
					.sign(algorithm);

		} catch (JWTCreationException e) {
			throw new RuntimeException("Error while generating token", e);
		}
	}

	private Instant generateExpirationDate(){
		return LocalDateTime.now().plusSeconds(accessTokenExpirationInSeconds).toInstant(ZoneOffset.of("-03:00"));
	}


	public String validateToken(String token){

		try {

			Algorithm algorithm = Algorithm.HMAC256(secret);

			return JWT.require(algorithm)
			                  .withIssuer("a-target")
			                  .build()
			                  .verify(token)
			                  .getSubject();

		} catch (JWTVerificationException e) {
			return "";
		}
	}
}
