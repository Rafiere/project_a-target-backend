package com.atarget.atargetbackend.config.thymeleaf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

public class ThymeleafConfig {

	@Bean
	public ResourceBundleMessageSource messageSource(){

		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("templates");

		return messageSource;
	}
}
