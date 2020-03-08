package com.iza.microserviceusermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iza.microserviceusermanagement.model.User;
import com.iza.microserviceusermanagement.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRespostory;
	
	//@Autowired
	//private PasswordEncoder passwordEncoder;
	
	public User save(User user) {
		//user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRespostory.save(user);
	}
	
	public User findByUsername(String username) {
		return userRespostory.findByUsername(username).orElse(null);
	}
	
	public List<String> findUser(List<Long> idList){
		return userRespostory.findByIdList(idList);
	}
}
