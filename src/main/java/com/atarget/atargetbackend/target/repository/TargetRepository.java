package com.atarget.atargetbackend.target.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetRepository<T> extends JpaRepository<T, String> {

}
