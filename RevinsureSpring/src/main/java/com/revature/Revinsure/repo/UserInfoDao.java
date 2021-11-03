package com.revature.Revinsure.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserInfo;

public interface UserInfoDao extends JpaRepository <UserInfo, User> {

	
}
