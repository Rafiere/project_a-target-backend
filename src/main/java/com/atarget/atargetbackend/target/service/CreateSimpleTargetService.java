package com.atarget.atargetbackend.target.service;

import com.atarget.atargetbackend.target.controller.request.CreateSimpleTargetRequest;
import com.atarget.atargetbackend.target.controller.response.CreateSimpleTargetResponse;
import com.atarget.atargetbackend.target.domain.SimpleTarget;
import com.atarget.atargetbackend.target.repository.SimpleTargetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateSimpleTargetService {

	private final SimpleTargetRepository simpleTargetRepository;

	public CreateSimpleTargetResponse execute(CreateSimpleTargetRequest createTargetRequest) {

		SimpleTarget createdSimpleTarget =
				SimpleTarget.of(createTargetRequest.name(), createTargetRequest.description());

		SimpleTarget savedSimpleTarget = simpleTargetRepository.save(createdSimpleTarget);

		return CreateSimpleTargetResponse.from(savedSimpleTarget);
	}
}
