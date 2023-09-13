package com.atarget.atargetbackend.timer.repository;

import com.atarget.atargetbackend.timer.domain.TimeInterval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeIntervalRepository extends JpaRepository<TimeInterval, String> {

}
