package com.atarget.atargetbackend.timer.service;

import com.atarget.atargetbackend.timer.controller.response.GetTimeCounterResponse;
import com.atarget.atargetbackend.timer.domain.TimeCounter;
import com.atarget.atargetbackend.timer.repository.TimeCounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllTimeCountersService {

	private final TimeCounterRepository timeCounterRepository;

	public List<GetTimeCounterResponse> execute() {

		List<TimeCounter> allTimersCount = timeCounterRepository.findAll();

		return allTimersCount.stream()
		                     .map(GetTimeCounterResponse::from)
		                     .collect(Collectors.toList());
	}
}
