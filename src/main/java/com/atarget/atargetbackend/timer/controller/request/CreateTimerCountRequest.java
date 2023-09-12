package com.atarget.atargetbackend.timer.controller.request;

import com.atarget.atargetbackend.timer.domain.enums.CountTimeMethod;
import com.atarget.atargetbackend.timer.domain.enums.TimerCountType;

public record CreateTimerCountRequest(String name, TimerCountType timerCountType, CountTimeMethod countTimeMethod) {

}
