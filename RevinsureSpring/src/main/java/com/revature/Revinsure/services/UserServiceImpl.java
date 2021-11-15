package com.revature.Revinsure.services;

import java.util.Date;

import org.apache.log4j.Logger;
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

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);
	
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
		log.debug("getUserByEmail called for email: " + email);
		return userDao.getUserByEmail(email);
	}

	@Override
	public boolean authenticate(User user) {
		log.debug("authenticate has been called for user: " + user.toString());
		User databaseUser = getUserByEmail(user.getEmail());
		boolean success = false;

		if (databaseUser != null && databaseUser.getPassword().equals(user.getPassword())) {
			success = true;
			log.debug("authentication was successful for databaseUser: " + databaseUser.toString());
		}

		return success;
	}

	@Override
	public User registerUser(User user) {
		log.debug("registerUser has been called for user: " + user.toString());
		user = userDao.save(user);
		if (user != null) {
			log.debug("registration was successful!");
			return user;
		}
		log.debug("The user obj is null!");
		return null;

	}

	public boolean registerUserInfo(UserInfo userInfo) {
		log.info("registerUserInfo has been called for userInfo: " + userInfo.toString());
		if (userInfoDao.save(userInfo) != null) {
			return true;
		}
		log.debug("registerUserInfo has returned false!");
		return false;
	}

	@Override
	public boolean updatePassword(User user, String password) {
		log.debug("updatePassword has been called for password: " + password);
		int result = userDao.updatePasswordByEmail(password, user.getEmail());
		boolean success = false;
		if (result > 0) {
			success = true;
		}
		return success;
	}

	@Override
	public boolean updateEmail(User user, String email) {
		log.info("updateEmail has been called for user id: " + user.getId());
		boolean success = false;

		if (userDao.updateUsername(email, user.getId()) > 0) {
			success = true;
		}
		return success;

	}

	@Override
	public boolean updateUserInfo(User user, UserInfo userInfo) {
		log.info("updateUserInfo called for user id: + " + user.getId());
		boolean success = false;

		if (userInfoDao.updateInfo(userInfo.getFirstName(), userInfo.getLastName(), userInfo.getAddress(),
				userInfo.getCity(), userInfo.getState(), userInfo.getZip(), user.getId()) > 0) {
			success = true;
			log.debug("updateUserInfo was successful!");
		}
		return success;

	}

	@Override
	public boolean createOrUpdateCovidForm(User user, CovidQuestion covidForm) {
		log.info("createOrUpdateCovidForm was called for user id: " + user.getId());
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
					log.debug("covidForm was updated!");
				} else {
					covidQuestionDao.save(covidForm);
					success = true;
					log.debug("covidForm was created!");
				}

			} catch (Exception e) {
				log.warn("an exception was caught calling createOrUpdateCovidForm!");
				e.printStackTrace();

			}
		}

		return success;
	}

	@Override
	public boolean checkIfAfterFourteenDays(User user) {
		log.debug("checkAfterFourteenDays has been called for user id: " + user.getId());
		boolean isAfterFourteenDays = false; // don't show new COVID-19 form to user if the last time the user answered
												// is over 14 days

		Date currentDate = new Date();

		if (user != null) {
			CovidQuestion covid = covidQuestionDao.findByUser(user);

			if (covid == null || covid.getDateAnswered() == null) {
				isAfterFourteenDays = true; // show new COVID-19 form if it's null
				log.debug("covid form was null or form's submit date was null");
			} else {
				long difference = currentDate.getTime() - covid.getDateAnswered().getTime();
				double daysBetween = (difference / (1000 * 60 * 60 * 24));

				if (daysBetween >= 14) {
					log.debug("submission date of covidForm was more than 14 days ago");
					isAfterFourteenDays = true; // show new COVID-19 form to user if the last date they answered was
												// less than 14 days ago
				}
			}

		}

		return isAfterFourteenDays;
	}

	@Override
	public boolean updatePasswordByEmail(User user) {
		log.info("updatePasswordByEmail was called for user id: " + user.getId());
		int result = userDao.updatePasswordByEmail(user.getPassword(), user.getEmail());
		boolean success = false;
		if (result > 0) {
			success = true;
			log.debug("the call to updatePasswordByEmail was succesful!");
		}
		return success;
	}

	public UserInfo getUserInfo(User user) {
		log.debug("getUserInfo was called for user id: " + user.getId());
		return userInfoDao.getUserInfoByUser(user);
	}

}
