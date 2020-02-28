package com.personal.accident.demo.service;

import java.util.List;

import com.personal.accident.demo.dto.User;
import com.personal.accident.demo.model.UserModel;

public interface UserService {

	

	Boolean saveUser(UserModel u); 
	Boolean findMail(UserModel u);
	List<UserModel> searchUser(UserModel usermodel);
	
}
