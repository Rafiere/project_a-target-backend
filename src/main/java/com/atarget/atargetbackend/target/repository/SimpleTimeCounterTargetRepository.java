package com.atarget.atargetbackend.target.repository;

import com.atarget.atargetbackend.target.domain.SimpleTimeCounterTarget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleTimeCounterTargetRepository extends JpaRepository<SimpleTimeCounterTarget, String> {

}
