package com.revature.Revinsure.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.Revinsure.models.User;

@Repository("userDao")
@Transactional
public interface UserDao extends JpaRepository <User, Integer> {
	
	@Modifying
	@Query("UPDATE User u SET u.email = ?1 WHERE u.id = ?2")
	int updateUsername(String email, int id);
	
	
	@Modifying
	@Query("UPDATE User u SET u.password = ?1 WHERE u.id = ?2")
	int updatePassword(String password, int id);
	

	
	User getUserByEmail(String email);

}
