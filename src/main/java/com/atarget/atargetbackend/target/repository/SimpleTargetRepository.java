package com.atarget.atargetbackend.target.repository;

import com.atarget.atargetbackend.target.domain.SimpleTarget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleTargetRepository extends JpaRepository<SimpleTarget, String> {

}
