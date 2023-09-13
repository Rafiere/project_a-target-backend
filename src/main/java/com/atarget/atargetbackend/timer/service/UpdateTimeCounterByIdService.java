package com.atarget.atargetbackend.timer.service;

import com.atarget.atargetbackend.timer.controller.request.UpdateTimeCounterRequest;
import com.atarget.atargetbackend.timer.controller.response.GetTimeCounterResponse;
import com.atarget.atargetbackend.timer.domain.TimeCounter;
import com.atarget.atargetbackend.timer.repository.TimeCounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateTimeCounterByIdService {

	private final TimeCounterRepository timeCounterRepository;

	public GetTimeCounterResponse execute(UpdateTimeCounterRequest request, String timerCountId) {

		TimeCounter timeCounter = timeCounterRepository.findById(timerCountId)
		                                               .orElseThrow(() -> new IllegalArgumentException(
				                                            "A timer count with ID " +
				                                            timerCountId +
				                                            " does not exist."));

		timeCounter.update(request.newTimerCountName(),
		                   request.newTimerCountDescription(),
		                   request.durationToProcess()
		                         .duration(),
		                   request.durationToProcess()
		                         .operation());

		return GetTimeCounterResponse.from(timeCounter);
	}
}
