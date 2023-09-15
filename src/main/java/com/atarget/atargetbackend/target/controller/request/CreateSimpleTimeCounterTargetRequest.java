package com.atarget.atargetbackend.target.controller.request;

import com.atarget.atargetbackend.target.domain.enums.MetaType;
import com.atarget.atargetbackend.timer.controller.request.CreateTimeCounterRequest;

public record CreateSimpleTimeCounterTargetRequest(String name, String description, MetaType metaType, CreateTimeCounterRequest createTimeCounterRequest) {

}
