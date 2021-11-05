package com.revature.Revinsure.services;

import com.revature.Revinsure.models.CovidQuestion;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserInfo;

public interface UserService {
	
	//CovidQuestion will also be dealt with here
	
	User getUserByEmail(String email);
	
	boolean authenticate(User user);
	
	User registerUser(User user);
	
	boolean updatePassword(User user, String password);
	
	boolean updateEmail(User user, String email);
	
	boolean updateUserInfo(User user, UserInfo userInfo);
	
	boolean createCovidForm(User user, CovidQuestion covidform);
	
	boolean updateCovidForm(User user, CovidQuestion covidform);

	boolean registerUserInfo(UserInfo userInfo);

	
}
