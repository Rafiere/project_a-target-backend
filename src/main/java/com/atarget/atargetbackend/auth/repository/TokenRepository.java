package com.atarget.atargetbackend.auth.repository;

import com.atarget.atargetbackend.auth.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {

	@Query("SELECT t " +
	       "FROM Token t " +
	       "WHERE t.tokenText = :tokenText")
	Optional<Token> findTokenByTokenText(String tokenText);
}
