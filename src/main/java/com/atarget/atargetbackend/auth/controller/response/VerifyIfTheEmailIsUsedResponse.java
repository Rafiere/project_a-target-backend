package com.atarget.atargetbackend.auth.controller.response;

import com.atarget.atargetbackend.auth.service.wrapper.VerifyIfTheEmailIsUsedWrapper;

public record VerifyIfTheEmailIsUsedResponse(String email, boolean isUsed) {

	public static VerifyIfTheEmailIsUsedResponse from(final VerifyIfTheEmailIsUsedWrapper wrapper){

		return new VerifyIfTheEmailIsUsedResponse(wrapper.email(), wrapper.isUsed());
	}
}
