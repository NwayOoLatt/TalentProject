package com.personal.accident.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.accident.demo.dto.PolicyHolder;

public interface ProposalRepository extends JpaRepository<PolicyHolder, String>,ProposalCustom{

}
