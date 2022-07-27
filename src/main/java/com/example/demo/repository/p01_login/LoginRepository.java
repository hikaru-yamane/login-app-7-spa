package com.example.demo.repository.p01_login;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.p01_login.User;

public interface LoginRepository extends CrudRepository<User, Integer> {
	
	@Query("SELECT a.id, a.name, a.email, a.password, r.name AS role_name FROM (SELECT id, name, email, password, role_id FROM user_account WHERE email = :email) AS a JOIN user_role AS r ON a.role_id = r.id")
	User getUser(@Param("email") String email);
	
}
