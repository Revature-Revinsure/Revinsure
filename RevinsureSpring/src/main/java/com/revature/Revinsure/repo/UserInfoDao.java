package com.revature.Revinsure.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserInfo;

@Transactional
public interface UserInfoDao extends JpaRepository <UserInfo, Integer> {
	
	@Modifying
	@Query("UPDATE user_info u SET u.first_name = ?1, u.last_name = ?2, u.address = ?3, u.city = ?4, u.state = ?5, u.zip = ?6 WHERE u.user_id = ?7")
	public boolean updateInfo(String firstName, String lastName, String address, String city, String state, String zip, int id);

	
}
