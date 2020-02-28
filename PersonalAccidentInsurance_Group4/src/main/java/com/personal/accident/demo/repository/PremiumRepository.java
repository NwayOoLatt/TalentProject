package com.personal.accident.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.accident.demo.dto.Premium;

public interface PremiumRepository extends JpaRepository<Premium, Integer>{

}
