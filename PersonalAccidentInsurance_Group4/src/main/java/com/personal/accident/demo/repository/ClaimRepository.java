package com.personal.accident.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.accident.demo.dto.ClaimForm;

public interface ClaimRepository extends JpaRepository<ClaimForm, Integer>,ClaimRepositoryCustom{

}
