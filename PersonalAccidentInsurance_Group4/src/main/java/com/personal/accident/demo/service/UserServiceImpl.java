package com.personal.accident.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.accident.demo.dto.Payment;
import com.personal.accident.demo.dto.User;
import com.personal.accident.demo.model.UserModel;
import com.personal.accident.demo.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	
	@Autowired
	UserRepository  userrespository;
	@Override
	public Boolean saveUser(UserModel u) {
		
		// TODO Auto-generated method stub
		User user=new User();
		
		user.setName(u.getName());
		user.setEmail(u.getEmail());
		user.setPassword(u.getPassword());
		user.setStatus("active");
		userrespository.save(user);
		return true;
	}
	
	
	@Override
	public List<UserModel> searchUser(UserModel usermodel) {
		// TODO Auto-generated method stub
		
		User user = new User();
		UserModel model = new UserModel();
		List<UserModel> modellist = new ArrayList<UserModel>();
		List<User> usrlist = new ArrayList<User>();
		
		user.setId(usermodel.getId());
		user.setName(usermodel.getName());
		user.setPassword(usermodel.getPassword());

		usrlist = userrespository.findUser(user);
     
		System.out.println("----service1----" + usrlist);

		if (!usrlist.isEmpty()) {
			
			for (User u : usrlist) {
				model.setName(u.getName());
				model.setPassword(u.getPassword());
				model.setId(u.getId());
				System.out.println("-----ssssss----" + usrlist);
			}
			modellist.add(model);
		}

		System.out.println("---service----" + modellist);
		return modellist;
	}


	@Override
	public Boolean findMail(UserModel user) {
		// TODO Auto-generated method stub
		Boolean flag;
		User u=new User();
		u.setEmail(user.getEmail());
		List<User> userlist=new ArrayList<User>();
		
		userlist=userrespository.findMail(u);
		
		System.out.println("userlist"+userlist);
		
		if(userlist.isEmpty()) {
			flag=false;
		}
		else {
			flag=true;
		}
		return flag;
	}
	}

	


