package com.atarget.atargetbackend.auth.service;

import com.atarget.atargetbackend.auth.service.wrapper.VerifyIfTheEmailIsUsedWrapper;
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

	public VerifyIfTheEmailIsUsedWrapper execute(final String email){

		final boolean isEmailUsed = userEntityCommonValidationsUtils.verifyIfEmailIsAlreadyUsed(email, ShouldThrowAnException.DO_NOT_THROW);

		return VerifyIfTheEmailIsUsedWrapper.of(email, isEmailUsed);
	}
}
