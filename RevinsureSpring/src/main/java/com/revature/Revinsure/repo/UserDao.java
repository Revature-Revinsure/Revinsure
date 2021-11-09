package com.revature.Revinsure.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.Revinsure.models.User;

@Repository("userDao")
@Transactional
public interface UserDao extends JpaRepository <User, Integer> {
	
	@Modifying
	@Query("UPDATE User u SET u.email = ?1, u.password = ?2 WHERE u.id = ?3")
	boolean updateUser(String email, String password, int id);
	

	
	User getUserByEmail(String email);

}
