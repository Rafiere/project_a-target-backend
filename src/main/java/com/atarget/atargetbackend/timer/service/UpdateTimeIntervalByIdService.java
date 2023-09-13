package com.atarget.atargetbackend.timer.service;

import com.atarget.atargetbackend.timer.controller.request.UpdateTimeIntervalRequest;
import com.atarget.atargetbackend.timer.controller.response.GetTimerIntervalResponse;
import com.atarget.atargetbackend.timer.domain.TimeInterval;
import com.atarget.atargetbackend.timer.repository.TimeIntervalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class UpdateTimeIntervalByIdService {

	private final TimeIntervalRepository timeIntervalRepository;

	public GetTimerIntervalResponse execute(UpdateTimeIntervalRequest request, String timeIntervalId) {

		TimeInterval timeInterval = timeIntervalRepository.findById(timeIntervalId)
		                                                  .orElseThrow(() -> new IllegalArgumentException(
				                                                  "A time interval with ID " +
				                                                  timeIntervalId +
				                                                  " does not exist."));

		timeInterval.updateTimeInterval(request.newName(), request.newDescription());

		return GetTimerIntervalResponse.from(timeInterval);
	}
}
