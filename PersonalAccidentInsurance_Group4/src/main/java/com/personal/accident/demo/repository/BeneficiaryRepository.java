package com.personal.accident.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.accident.demo.dto.Beneficiary;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer>{

}
