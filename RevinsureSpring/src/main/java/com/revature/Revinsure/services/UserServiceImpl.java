package com.revature.Revinsure.services;

import java.util.Date;
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
		user = userDao.save(user);
		if (user != null) {

			return user;
		}

		return null;

	}

	public boolean registerUserInfo(UserInfo userInfo) {

		if (userInfoDao.save(userInfo) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePassword(User user, String password) {
		int result = userDao.updatePasswordByEmail(password, user.getEmail());
		boolean success = false;
		if (result > 0) {
			success = true;
		}
		return success;
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
				userInfo.getCity(), userInfo.getState(), userInfo.getZip(), user.getId()) > 0) {
			success = true;
		}
		return success;

	}

	@Override
	public boolean createOrUpdateCovidForm(User user, CovidQuestion covidForm) {
		boolean success = false;

		if (covidForm != null && covidForm.getDateAnswered() != null) {
			covidForm.setUser(user);

			try {
				CovidQuestion currentForm = covidQuestionDao.findByUser(user);
				if (currentForm != null) {

					currentForm.setAroundCovid(covidForm.isAroundCovid());
					currentForm.setHasCovid(covidForm.isHasCovid());
					currentForm.setDateAnswered(covidForm.getDateAnswered());

					covidQuestionDao.save(currentForm);
					success = true;
				} else {
					covidQuestionDao.save(covidForm);
					success = true;
				}

			} catch (Exception e) {
				e.printStackTrace();

			}
		}

		return success;
	}

	@Override
	public boolean checkIfAfterFourteenDays(User user) {
		boolean isAfterFourteenDays = false; // don't show new COVID-19 form to user if the last time the user answered
												// is over 14 days

		Date currentDate = new Date();

		if (user != null) {
			CovidQuestion covid = covidQuestionDao.findByUser(user);

			if (covid == null || covid.getDateAnswered() == null) {
				isAfterFourteenDays = true; // show new COVID-19 form if it's null
			} else {
				long difference = currentDate.getTime() - covid.getDateAnswered().getTime();
				double daysBetween = (difference / (1000 * 60 * 60 * 24));

				if (daysBetween >= 14) {
					isAfterFourteenDays = true; // show new COVID-19 form to user if the last date they answered was
												// less than 14 days ago
				}
			}

		}

		return isAfterFourteenDays;
	}

	@Override
	public boolean updatePasswordByEmail(User user) {
		int result = userDao.updatePasswordByEmail(user.getPassword(), user.getEmail());
		boolean success = false;
		if (result > 0) {
			success = true;
		}
		return success;
	}

	public UserInfo getUserInfo(User user) {
		return userInfoDao.getUserInfoByUser(user);
	}

}
