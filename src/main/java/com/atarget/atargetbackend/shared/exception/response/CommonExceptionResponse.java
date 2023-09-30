package com.atarget.atargetbackend.shared.exception.response;

public record CommonExceptionResponse(String message, String additionalInfo){

	public static CommonExceptionResponse of(final String message, final String additionalInfo){
		return new CommonExceptionResponse(message, additionalInfo);
	}
}
