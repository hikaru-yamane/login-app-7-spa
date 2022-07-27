package com.example.demo.service.p08_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.p08_user.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public boolean registerUser(String name, String email, String password) {
		return repository.registerUser(name, email, password);
	}

}
