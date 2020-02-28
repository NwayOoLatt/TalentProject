package com.personal.accident.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.accident.demo.dto.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
