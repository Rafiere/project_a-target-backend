package com.atarget.atargetbackend.timer.controller.request;

import com.atarget.atargetbackend.shared.exception.custom.BusinessRuleException;
import com.atarget.atargetbackend.timer.domain.enums.TimeCounterMethod;
import com.atarget.atargetbackend.timer.domain.enums.TimeCounterType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTimeCounterRequest(@NotBlank String name, String description,
                                       @NotNull TimeCounterType timeCounterType,
                                       @NotNull TimeCounterMethod timeCounterMethod,
                                       String targetAssignedToTimeCounterId) {

	public void validate() {

		if (timeCounterType.equals(TimeCounterType.ASSIGNED) &&
		    (targetAssignedToTimeCounterId ==
		     null ||
		     targetAssignedToTimeCounterId.isBlank())) {

			throw BusinessRuleException.of("A time counter of type ASSIGNED must have a target assigned to it.");
		}
	}
}
