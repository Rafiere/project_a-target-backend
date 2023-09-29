package com.atarget.atargetbackend.auth.service.wrapper;

public record VerifyIfTheEmailIsUsedWrapper(String email, boolean isUsed) {

	public static VerifyIfTheEmailIsUsedWrapper of(String email, boolean isUsed){

		return new VerifyIfTheEmailIsUsedWrapper(email, isUsed);
	}
}
