package com.atarget.atargetbackend.timer.controller.request;

import com.atarget.atargetbackend.timer.domain.enums.TimeCounterMethod;
import com.atarget.atargetbackend.timer.domain.enums.TimeCounterType;

public record CreateTimeCounterRequest(String name, String description, TimeCounterType timeCounterType, TimeCounterMethod timeCounterMethod) {

}
