package com.atarget.atargetbackend.shared.exception.custom;

public class BusinessRuleException extends RuntimeException {

	private BusinessRuleException(final String message) {
		super(message);
	}

	public static BusinessRuleException of(final String message) {
		return new BusinessRuleException(message);
	}
}
