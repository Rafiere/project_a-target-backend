package com.atarget.atargetbackend.target.service;

import com.atarget.atargetbackend.target.controller.request.CreateSimpleTargetRequest;
import com.atarget.atargetbackend.target.controller.response.CreateSimpleTargetResponse;
import com.atarget.atargetbackend.target.domain.SimpleTarget;
import com.atarget.atargetbackend.target.repository.TargetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateSimpleTargetService {

	private final TargetRepository<SimpleTarget> targetRepository;

	public CreateSimpleTargetResponse execute(CreateSimpleTargetRequest createTargetRequest) {

		SimpleTarget createdSimpleTarget =
				SimpleTarget.of(createTargetRequest.name(), createTargetRequest.description());

		SimpleTarget savedSimpleTarget = targetRepository.save(createdSimpleTarget);

		return CreateSimpleTargetResponse.from(savedSimpleTarget);
	}
}
