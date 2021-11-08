package com.revature.Revinsure.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.Revinsure.models.User;

public interface UserDao extends JpaRepository <User, Integer> {

	User findByEmail(String email);
	
	
}
