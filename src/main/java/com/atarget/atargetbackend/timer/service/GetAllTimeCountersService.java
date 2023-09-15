package com.atarget.atargetbackend.timer.service;

import com.atarget.atargetbackend.timer.controller.response.GetTimeCounterResponse;
import com.atarget.atargetbackend.timer.domain.TimeCounter;
import com.atarget.atargetbackend.timer.repository.TimeCounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GetAllTimeCountersService {

	private final TimeCounterRepository timeCounterRepository;

	public List<GetTimeCounterResponse> execute() {

		List<TimeCounter> allTimersCount = timeCounterRepository.findAll();

		return allTimersCount.stream()
		                     .map(GetTimeCounterResponse::from)
		                     .toList();
	}
}
