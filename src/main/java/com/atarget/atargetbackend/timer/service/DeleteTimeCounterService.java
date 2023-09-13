package com.atarget.atargetbackend.timer.service;

import com.atarget.atargetbackend.timer.repository.TimeCounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteTimeCounterService {

	private final TimeCounterRepository timeCounterRepository;

	public void execute(String timerCountId){

		timeCounterRepository.deleteById(timerCountId);
	}
}
