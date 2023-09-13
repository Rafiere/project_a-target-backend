package com.atarget.atargetbackend.timer.service;

import com.atarget.atargetbackend.timer.controller.response.GetTimeCounterResponse;
import com.atarget.atargetbackend.timer.domain.TimeCounter;
import com.atarget.atargetbackend.timer.repository.TimeCounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GetTimeCounterByIdService {

	private final TimeCounterRepository timeCounterRepository;

	public GetTimeCounterResponse execute(String timerCountId) {

		TimeCounter timeCounter = timeCounterRepository.findById(timerCountId)
		                                               .orElseThrow(() -> new IllegalArgumentException(
				                                            "A time counter with ID " +
				                                            timerCountId +
				                                            " does not exist."));

		return GetTimeCounterResponse.from(timeCounter);
	}
}
