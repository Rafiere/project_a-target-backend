package com.atarget.atargetbackend.target.domain.interfaces;

import com.atarget.atargetbackend.target.domain.enums.MetaType;

public interface Targetable {

	String getId();

	String getName();

	String getDescription();

	MetaType getMetaType();
}
