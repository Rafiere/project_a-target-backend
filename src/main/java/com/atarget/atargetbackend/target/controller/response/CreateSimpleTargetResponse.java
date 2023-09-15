package com.atarget.atargetbackend.target.controller.response;

import com.atarget.atargetbackend.target.domain.SimpleTarget;
import com.atarget.atargetbackend.target.domain.enums.MetaType;

public record CreateSimpleTargetResponse(String id, String name, String description, MetaType metaType) {

	public static CreateSimpleTargetResponse from(SimpleTarget simpleTarget){
		return new CreateSimpleTargetResponse(
				simpleTarget.getId(),
				simpleTarget.getName(),
				simpleTarget.getDescription(),
				simpleTarget.getMetaType()
		);
	}
}
