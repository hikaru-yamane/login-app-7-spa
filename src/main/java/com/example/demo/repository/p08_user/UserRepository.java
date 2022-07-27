package com.example.demo.repository.p08_user;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.p01_login.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	@Modifying
	@Query("INSERT INTO user_account (name, email, password, role_id) values (:name, :email, :password, 2)")
	boolean registerUser(@Param("name") String name, @Param("email") String email, @Param("password") String password);
	
}
