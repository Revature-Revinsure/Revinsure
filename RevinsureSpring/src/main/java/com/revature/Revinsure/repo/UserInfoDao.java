package com.revature.Revinsure.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserInfo;

@Repository("userInfoDao")
@Transactional
public interface UserInfoDao extends JpaRepository <UserInfo, Integer> {
	
	@Transactional
	@Modifying
	@Query("UPDATE UserInfo u SET u.firstName = ?1, u.lastName = ?2, u.address = ?3, u.city = ?4, u.state = ?5, u.zip = ?6 WHERE u.user.id = ?7")
	int updateInfo(String firstName, String lastName, String address, String city, String state, String zip, int id);

	UserInfo getUserInfoByUser(User user);
	
}
