package com.atarget.atargetbackend.shared.exception.custom;

import com.atarget.atargetbackend.shared.exception.custom.enums.Resources;

public class ResourceNotFoundException extends RuntimeException {

	private ResourceNotFoundException(final Resources resource, final String identifier) {
		super("The resource \"" +
		      resource +
		      "\" with the identifier \"" +
		      identifier +
		      "\" was not found.");
	}

	public static ResourceNotFoundException of(final Resources resource, final String  identifier) {
		return new ResourceNotFoundException(resource, identifier);
	}
}
