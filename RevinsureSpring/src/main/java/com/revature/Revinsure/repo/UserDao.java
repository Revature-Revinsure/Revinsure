package com.revature.Revinsure.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.revature.Revinsure.models.User;

@Transactional
public interface UserDao extends JpaRepository <User, Integer> {
	
	@Modifying
	@Query("UPDATE users u SET u.email = ?1, u.password = ?2 WHERE u.user_id = ?3")
	public boolean updateUser(String email, String password, int id);

}
