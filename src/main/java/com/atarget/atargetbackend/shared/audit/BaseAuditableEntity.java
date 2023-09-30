package com.atarget.atargetbackend.shared.audit;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseAuditableEntity {

	@CreatedBy private String createdBy;
	@CreatedDate private final LocalDateTime createdDate = LocalDateTime.now();
	@LastModifiedDate private LocalDateTime updatedAt;
}

