package com.example.demo.service.p01_login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.p01_login.User;
import com.example.demo.repository.p01_login.LoginRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
	@Autowired
	private LoginRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		if (email == null || "".equals(email)) {
			throw new UsernameNotFoundException(email + "is not found");
		}
		try {
			User user = repository.getUser(email);
			if (user != null) {
				return new UserDetailsImpl(user);
			} else {
				throw new UsernameNotFoundException(email + "is not found");
			}
		} catch (EmptyResultDataAccessException e) {
				throw new UsernameNotFoundException(email + "is not found");
		}
	}

}