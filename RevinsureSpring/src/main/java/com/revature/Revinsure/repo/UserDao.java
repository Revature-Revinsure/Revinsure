package com.revature.Revinsure.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;


import com.revature.Revinsure.models.User;

@Repository("userDao")
public interface UserDao extends JpaRepository <User, Integer> {

	@Transactional
	@Modifying
	@Query("update User u set u.userPassword = ?1 where u.email = ?2")
	boolean updatePassword(String password, String email);

	User findByEmail(String email);

	User getUserByEmail(String email);

	
	
}
