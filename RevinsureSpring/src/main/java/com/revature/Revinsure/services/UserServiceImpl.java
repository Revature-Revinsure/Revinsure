package com.revature.Revinsure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.revature.Revinsure.models.CovidQuestion;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserInfo;
import com.revature.Revinsure.repo.CovidQuestionDao;
import com.revature.Revinsure.repo.UserDao;
import com.revature.Revinsure.repo.UserInfoDao;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private CovidQuestionDao covidQuestionDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean authenticate(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePassword(User user, String password) {
		
		return userDao.updateUser(user.getEmail(), password, user.getId());
	
	}

	@Override
	public boolean updateEmail(User user, String email) {
		
		return userDao.updateUser(email, user.getPassword(), user.getId());
	
	}

	@Override
	public boolean updateUserInfo(User user, UserInfo userInfo) {
		
		return userInfoDao.updateInfo(userInfo.getFirstName(), userInfo.getLastName(), userInfo.getAddress(), userInfo.getCity(), userInfo.getState(), userInfo.getZip(), user.getId());
		
	}

	@Override
	public boolean createCovidForm(User user, CovidQuestion covidform) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCovidForm(User user, CovidQuestion covidform) {
		// TODO Auto-generated method stub
		return false;
	}


}
