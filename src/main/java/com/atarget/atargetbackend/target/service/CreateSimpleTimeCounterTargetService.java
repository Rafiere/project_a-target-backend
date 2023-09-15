package com.atarget.atargetbackend.target.service;

import com.atarget.atargetbackend.target.controller.request.CreateSimpleTimeCounterTargetRequest;
import com.atarget.atargetbackend.target.controller.response.CreateSimpleTimeCounterTargetResponse;
import com.atarget.atargetbackend.target.domain.SimpleTimeCounterTarget;
import com.atarget.atargetbackend.target.repository.TargetRepository;
import com.atarget.atargetbackend.timer.domain.TimeCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateSimpleTimeCounterTargetService {

	private final TargetRepository<SimpleTimeCounterTarget> targetRepository;

	public CreateSimpleTimeCounterTargetResponse execute(CreateSimpleTimeCounterTargetRequest request) {

		TimeCounter timeCounterForTheTarget = TimeCounter.of(request.createTimeCounterRequest()
		                                                            .name(),
		                                                     request.createTimeCounterRequest()
		                                                            .description(),
		                                                     request.createTimeCounterRequest()
		                                                            .timeCounterType(),
		                                                     request.createTimeCounterRequest()
		                                                            .timeCounterMethod());

		SimpleTimeCounterTarget createdSimpleTimeCounterTarget =
				SimpleTimeCounterTarget.of(request.name(), request.description(), timeCounterForTheTarget);

		SimpleTimeCounterTarget savedTarget = targetRepository.save(createdSimpleTimeCounterTarget);

		return CreateSimpleTimeCounterTargetResponse.from(savedTarget);
	}
}
