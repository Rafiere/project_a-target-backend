package com.atarget.atargetbackend.target.controller.request;

import com.atarget.atargetbackend.target.domain.enums.MetaType;

import java.math.BigDecimal;

public record CreateSimpleQuantifierTargetRequest(String name, String description, MetaType metaType, BigDecimal quantifier) {

}
