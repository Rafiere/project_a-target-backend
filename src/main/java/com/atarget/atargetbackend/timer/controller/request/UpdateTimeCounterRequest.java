package com.atarget.atargetbackend.timer.controller.request;

public record UpdateTimeCounterRequest(String newTimerCountName, String newTimerCountDescription, UpdateTimeCounterDurationRequest durationToProcess) {

}
