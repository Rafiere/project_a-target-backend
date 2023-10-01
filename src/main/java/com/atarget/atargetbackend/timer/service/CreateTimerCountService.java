package com.atarget.atargetbackend.timer.service;

import com.atarget.atargetbackend.timer.controller.request.CreateTimeCounterRequest;
import com.atarget.atargetbackend.timer.controller.response.CreateTimeCounterResponse;
import com.atarget.atargetbackend.timer.domain.TimeCounter;
import com.atarget.atargetbackend.timer.repository.TimeCounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateTimerCountService {

	private final TimeCounterRepository timeCounterRepository;

	public CreateTimeCounterResponse execute(final CreateTimeCounterRequest request) {

		//TODO: Criar wrappers e implementar lógica de acordo com o tipo de timecounter que for criado.

		final TimeCounter createdTimeCounter =
				TimeCounter.of(request.name(), request.description(), request.timeCounterType(), request.timeCounterMethod());

		timeCounterRepository.save(createdTimeCounter);

		return CreateTimeCounterResponse.from(createdTimeCounter);
	}
}
