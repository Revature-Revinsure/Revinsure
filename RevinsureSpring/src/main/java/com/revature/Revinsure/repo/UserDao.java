package com.revature.Revinsure.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.Revinsure.models.User;

@Repository("userDao")
public interface UserDao extends JpaRepository <User, Integer> {
	
	User getUserByEmail(String email);
	
}
