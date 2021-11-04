package com.revature.Revinsure.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.Revinsure.models.CovidQuestion;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserInfo;
import com.revature.Revinsure.repo.CovidQuestionDao;
import com.revature.Revinsure.repo.UserDao;
import com.revature.Revinsure.repo.UserInfoDao;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao uDao;
	
	@Autowired 
	private UserInfoDao uiDao;
	
	@Autowired
	private CovidQuestionDao cqDao;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEmail(User user, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserInfo(User user, UserInfo userInfo) {
		// TODO Auto-generated method stub
		return false;
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
