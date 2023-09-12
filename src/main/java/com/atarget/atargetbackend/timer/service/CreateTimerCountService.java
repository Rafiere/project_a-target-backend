package com.atarget.atargetbackend.timer.service;

import com.atarget.atargetbackend.timer.controller.request.CreateTimerCountRequest;
import com.atarget.atargetbackend.timer.controller.response.CreateTimerCountResponse;
import com.atarget.atargetbackend.timer.domain.TimerCount;
import com.atarget.atargetbackend.timer.repository.TimerCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTimerCountService {

	private TimerCountRepository timerCountRepository;

	public CreateTimerCountResponse execute(CreateTimerCountRequest request) {

		TimerCount createdTimerCount =
				TimerCount.of(request.name(), request.timerCountType(), request.countTimeMethod());

		timerCountRepository.save(createdTimerCount);

		return CreateTimerCountResponse.of(createdTimerCount);
	}
}
