package com.atarget.atargetbackend.timer.repository;

import com.atarget.atargetbackend.timer.domain.TimeCounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeCounterRepository extends JpaRepository<TimeCounter, String> {

}
