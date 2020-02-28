package com.personal.accident.demo.repository;

import java.util.List;

import com.personal.accident.demo.dto.User;

public interface UserRepositoryCustom {

	List<User> findUser(User user);
	List<User> findMail(User user);
}
