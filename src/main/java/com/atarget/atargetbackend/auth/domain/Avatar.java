package com.atarget.atargetbackend.auth.domain;

import jakarta.persistence.Embeddable;
import org.springframework.data.jpa.domain.AbstractAuditable;

@Embeddable
public class Avatar {

	private String fileUrl;
}
