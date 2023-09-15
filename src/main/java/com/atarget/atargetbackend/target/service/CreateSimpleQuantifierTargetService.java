package com.atarget.atargetbackend.target.service;

import com.atarget.atargetbackend.target.controller.request.CreateSimpleQuantifierTargetRequest;
import com.atarget.atargetbackend.target.controller.response.CreateSimpleQuantifierTargetResponse;
import com.atarget.atargetbackend.target.domain.SimpleQuantifierTarget;
import com.atarget.atargetbackend.target.repository.TargetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateSimpleQuantifierTargetService {

	private final TargetRepository<SimpleQuantifierTarget> targetRepository;

	public CreateSimpleQuantifierTargetResponse execute(CreateSimpleQuantifierTargetRequest request){

		SimpleQuantifierTarget createdSimpleQuantifierTarget = SimpleQuantifierTarget.of(request.name(), request.description(), request.quantifier());

		SimpleQuantifierTarget savedSimpleQuantifierTarget = targetRepository.save(createdSimpleQuantifierTarget);

		return CreateSimpleQuantifierTargetResponse.from(savedSimpleQuantifierTarget);
	}
}
