package com.atarget.atargetbackend.target.controller.response;

import com.atarget.atargetbackend.target.domain.SimpleTimeCounterTarget;
import com.atarget.atargetbackend.target.domain.enums.MetaType;
import com.atarget.atargetbackend.timer.controller.response.CreateTimeCounterResponse;

public record CreateSimpleTimeCounterTargetResponse(String name, String description, MetaType metaType,
                                                    CreateTimeCounterResponse timeCounterResponse) {

	public static CreateSimpleTimeCounterTargetResponse from(SimpleTimeCounterTarget simpleTimeCounterTarget) {

		return new CreateSimpleTimeCounterTargetResponse(simpleTimeCounterTarget.getName(),
		                                                 simpleTimeCounterTarget.getDescription(),
		                                                 simpleTimeCounterTarget.getMetaType(),
		                                                 CreateTimeCounterResponse.from(simpleTimeCounterTarget.getTimeCounter()));
	}
}
