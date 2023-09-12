package com.atarget.atargetbackend.timer.repository;

import com.atarget.atargetbackend.timer.domain.TimerCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimerCountRepository extends JpaRepository<TimerCount, String> {

}
