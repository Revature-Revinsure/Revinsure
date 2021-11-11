package com.revature.Revinsure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.revature.Revinsure.models.Claim;
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

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public boolean authenticate(User user) {

		User databaseUser = getUserByEmail(user.getEmail());
		boolean success = false;

		if (databaseUser != null && databaseUser.getPassword().equals(user.getPassword())) {
			success = true;
		}

		return success;
	}

	@Override
	public User registerUser(User user) {

		System.out.println(user);
		user = userDao.save(user);
		if (user.getId() > 0) {

			System.out.println(user);

			return user;
		}

		return null;

	}

	public boolean registerUserInfo(UserInfo userInfo) {
		System.out.println(userInfo);
		if (userInfoDao.save(userInfo) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePassword(User user, String password) {
		boolean success = false;
		if (userDao.updatePassword(password, user.getId()) > 0) {
			success = true;
		}
		return success;
//		return userDao.updateUser(user.getEmail(), password, user.getId());

	}

	@Override
	public boolean updateEmail(User user, String email) {
		boolean success = false;

		if (userDao.updateUsername(email, user.getId()) > 0) {
			success = true;
		}
		return success;

	}

	@Override
	public boolean updateUserInfo(User user, UserInfo userInfo) {

		boolean success = false;

		if (userInfoDao.updateInfo(userInfo.getFirstName(), userInfo.getLastName(), userInfo.getAddress(),
				userInfo.getCity(), userInfo.getState(), userInfo.getZip(), user.getId())>0) {
			success = true;
		}
		return success;

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
	public UserInfo getUserInfo(User user) {
		return userInfoDao.getUserInfoByUser(user);
	}

}
