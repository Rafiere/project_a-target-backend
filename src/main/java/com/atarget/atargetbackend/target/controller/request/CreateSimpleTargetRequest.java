package com.atarget.atargetbackend.target.controller.request;

import com.atarget.atargetbackend.target.domain.enums.MetaType;

public record CreateSimpleTargetRequest(String name, String description, MetaType metaType) {

}
