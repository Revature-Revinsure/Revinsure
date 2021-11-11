package com.revature.Revinsure.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;
 

import com.revature.Revinsure.models.User;

@Repository("userDao")
@Transactional
public interface UserDao extends JpaRepository <User, Integer> {

	@Transactional
	@Modifying
	@Query("update User u set u.password = ?1 where u.email = ?2")
	int updatePasswordByEmail(String password, String email);
	
	@Modifying
	@Query("UPDATE User u SET u.email = ?1 WHERE u.id = ?2")
	int updateUsername(String email, int id);
	
	

	
	User getUserByEmail(String email);

}
