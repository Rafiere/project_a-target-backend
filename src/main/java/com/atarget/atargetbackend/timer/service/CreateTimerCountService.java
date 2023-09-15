package com.atarget.atargetbackend.timer.service;

import com.atarget.atargetbackend.timer.controller.request.CreateTimeCounterRequest;
import com.atarget.atargetbackend.timer.controller.response.CreateTimeCounterResponse;
import com.atarget.atargetbackend.timer.domain.TimeCounter;
import com.atarget.atargetbackend.timer.repository.TimeCounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateTimerCountService {

	private TimeCounterRepository timeCounterRepository;

	public CreateTimeCounterResponse execute(CreateTimeCounterRequest request) {

		TimeCounter createdTimeCounter =
				TimeCounter.of(request.name(), request.description(), request.timeCounterType(), request.timeCounterMethod());

		timeCounterRepository.save(createdTimeCounter);

		return CreateTimeCounterResponse.from(createdTimeCounter);
	}
}
