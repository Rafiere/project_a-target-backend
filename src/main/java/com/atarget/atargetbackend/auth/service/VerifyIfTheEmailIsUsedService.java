package com.atarget.atargetbackend.auth.service;

import com.atarget.atargetbackend.shared.utils.validation.ShouldThrowAnException;
import com.atarget.atargetbackend.shared.utils.validation.UserEntityCommonValidationsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VerifyIfTheEmailIsUsedService {

	private final UserEntityCommonValidationsUtils userEntityCommonValidationsUtils;

	public Boolean execute(String email){

		return userEntityCommonValidationsUtils.verifyIfEmailIsAlreadyUsed(email, ShouldThrowAnException.DO_NOT_THROW);
	}
}
