package com.revature.Revinsure.services;

import org.springframework.beans.factory.annotation.Autowired;
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
	public boolean createCovidForm(User user, CovidQuestion covidForm) {
		boolean success = false;
		
		if(covidForm != null && covidForm.getDateAnswered() != null) {
			covidForm.setUser(user);
			
			try {
				covidQuestionDao.save(covidForm);
				success = true;
			}
			catch(Exception e) {
				e.printStackTrace();
				
			}
		}
		
		return success;
	}

	@Override
	public boolean updateCovidForm(User user, CovidQuestion covidForm) {
		boolean success = false;
		
		if(covidForm != null && covidForm.getDateAnswered() != null) {
			covidForm.setUser(user);
			
			try {
				covidQuestionDao.updateCovidQuestion(covidForm);
				success = true;
			}
			catch(Exception e) {
				e.printStackTrace();
				
			}
		}
		
		return success;
	}

}
