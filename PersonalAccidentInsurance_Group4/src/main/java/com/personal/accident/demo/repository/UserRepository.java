package com.personal.accident.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.accident.demo.dto.User;

public interface UserRepository extends JpaRepository<User, Integer>,UserRepositoryCustom{

}
