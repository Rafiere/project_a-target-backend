package com.atarget.atargetbackend.shared.exception.custom;

public class BusinessRuleException extends RuntimeException {

	private BusinessRuleException(String message) {
		super(message);
	}

	public static BusinessRuleException of(String message) {
		return new BusinessRuleException(message);
	}
}
