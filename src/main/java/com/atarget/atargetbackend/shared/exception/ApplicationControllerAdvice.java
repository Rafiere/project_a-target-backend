package com.atarget.atargetbackend.shared.exception;

import com.atarget.atargetbackend.shared.exception.custom.BusinessRuleException;
import com.atarget.atargetbackend.shared.exception.custom.ResourceNotFoundException;
import com.atarget.atargetbackend.shared.exception.response.CommonExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CommonExceptionResponse handleResourceNotFoundException(ResourceNotFoundException exception) {
		return CommonExceptionResponse.of(exception.getMessage(), "");
	}

	@ExceptionHandler(BusinessRuleException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public CommonExceptionResponse handleBusinessRuleException(BusinessRuleException exception) {
		return CommonExceptionResponse.of(exception.getMessage(), "");
	}
}
