package com.atarget.atargetbackend.target.controller.response;

import com.atarget.atargetbackend.target.domain.SimpleQuantifierTarget;
import com.atarget.atargetbackend.target.domain.enums.MetaType;

import java.math.BigDecimal;

public record CreateSimpleQuantifierTargetResponse(String id, String name, String description, MetaType metaType, BigDecimal quantity) {

	public static CreateSimpleQuantifierTargetResponse from(SimpleQuantifierTarget simpleQuantifierTarget){

		return new CreateSimpleQuantifierTargetResponse(
				simpleQuantifierTarget.getId(),
				simpleQuantifierTarget.getName(),
				simpleQuantifierTarget.getDescription(),
				simpleQuantifierTarget.getMetaType(),
				simpleQuantifierTarget.getQuantity()
		);
	}
}
