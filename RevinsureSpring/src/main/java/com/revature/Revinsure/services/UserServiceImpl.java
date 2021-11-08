package com.revature.Revinsure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.revature.Revinsure.models.CovidQuestion;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserInfo;
import com.revature.Revinsure.repo.CovidQuestionDao;
import com.revature.Revinsure.repo.UserDao;
import com.revature.Revinsure.repo.UserInfoDao;

@Service
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
		return userDao.findByEmail(email);
	}

	@Override
	public boolean authenticate(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User registerUser(User user) {
		
		System.out.println(user);
		user = userDao.save(user);
		if(user.getId()>0) {

			System.out.println(user);

			return user;
		}
		
		return null;
				
	}
	public boolean registerUserInfo(UserInfo userInfo) {
		System.out.println(userInfo);
		if(userInfoDao.save(userInfo)!=null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePassword(User user, String password) {
		boolean result = userDao.updatePassword(password, user.getEmail());
		return result;			
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

	@Override
	public boolean updatePasswordByEmail(User user) {
		boolean result = userDao.updatePassword(user.getPassword(), user.getEmail());
		return result;	
	}


}
