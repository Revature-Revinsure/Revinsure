package com.revature.Revinsure.controllers;

import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.revature.Revinsure.models.CovidQuestion;
import com.revature.Revinsure.models.Message;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserInfo;
import com.revature.Revinsure.models.UserType;
import com.revature.Revinsure.services.UserService;

@RestController("userController")
@RequestMapping("/user")
@CrossOrigin(origins = { "http://localhost:4200" }, allowCredentials = "true")
public class UserController {

	@Autowired
	private UserService userService;

	public UserController() {

	}

	public User getUserSession(HttpSession session) {

		User u = null;

		if (session.getAttribute("user") == null) {

			u = new User();
			u.setId(-1);

		} else {

			u = (User) session.getAttribute("user");

		}

		return u;

	}

	@PostMapping(value = "/updatePassword")
	public void updatePassword(@RequestBody String password, HttpServletResponse response, HttpSession session) {

		User u = this.getUserSession(session);

		if (u.getId() == -1) {

			response.setStatus(400);

		} else {

			userService.updatePassword(u, password);
			response.setStatus(200);
			User currentUser = userService.getUserByEmail(u.getEmail());
			session.setAttribute("user", currentUser);

		}

	}

	@PutMapping(value = "/updatePassword")
	public boolean changePasswordAfterLogin(HttpSession session, @RequestBody User user) {

		User oldUser = (User) session.getAttribute("user");
		boolean result = userService.updatePassword(oldUser, user.getPassword());

		return result;

	}

	@PostMapping(value = "/updateEmail")
	public void updateEmail(@RequestBody String email, HttpServletResponse response, HttpSession session) {

		User u = this.getUserSession(session);

		if (u.getId() == -1) {

			response.setStatus(400);

		} else {

			userService.updateEmail(u, email);
			response.setStatus(200);
			User currentUser = userService.getUserByEmail(email);
			session.setAttribute("user", currentUser);

		}

	}

	@PostMapping(value = "/updateUserInfo")
	public void updateUserInfo(@RequestBody UserInfo userInfo, HttpServletResponse response, HttpSession session) {

		User u = this.getUserSession(session);

		if (u.getId() == -1) {

			response.setStatus(400);

		} else {

			userService.updateUserInfo(u, userInfo);
			response.setStatus(200);
			session.setAttribute("user", u);

		}
	}

	@PostMapping(value = "/register")
	public User register(HttpSession session, @RequestBody User user) {
//		user.setInfo(userInfo);
		
		user = userService.registerUser(user);
		session.setAttribute("user", user);
		return user;

	}

	@PostMapping(value = "/registerInfo")
	public boolean register(HttpSession session, @RequestBody UserInfo userInfo) {
//		user.setInfo(userInfo);

		userInfo.setUser((User) session.getAttribute("user"));
		
		boolean result = userService.registerUserInfo(userInfo);

		return result;

	}

	@PostMapping(value = "/register/check")
	public boolean checkUser(@RequestBody User user) {
		
		User result = userService.getUserByEmail(user.getEmail());
		
		if (result == null) {

			return false;

		}
		return true;

	}

	@PutMapping(value = "/updatePasswordByEmail")
	public boolean changePasswordBeforeLogin(@RequestBody User user) {
		
		boolean result = userService.updatePasswordByEmail(user);

		return result;

	}

	@PostMapping(value = "/login")
	public User login(HttpSession session, @RequestBody User user) {
		
		boolean isAuthenticated = userService.authenticate(user);
		
		User currentUser = userService.getUserByEmail(user.getEmail());
		
		if(isAuthenticated == true) {
			session.setAttribute("user", currentUser);
		} else {
			currentUser = null;
		}
		
		return currentUser;
	}
	
	@GetMapping(value = "/covid")
	public boolean checkIfDateIsAfterFourteenDays(HttpSession session) {
		User user = (User) session.getAttribute("user");
		
		return userService.checkIfAfterFourteenDays(user);
	}
	
	@PostMapping(value = "/covid")
	public Message createOrUpdateCovidAnswer(HttpSession session, @RequestBody CovidQuestion covidQuestion) {
		Message message = new Message();
		User user = (User) session.getAttribute("user");
		
		if(userService.createOrUpdateCovidForm(user, covidQuestion)) {
			message.setMessage("Successfully added or updated Covid Questionnaire.");
		} else {
			message.setMessage("Adding or updating Covid Questionnaire failed.");
		}
		
		return message;
	}
		

	@GetMapping(value = "/userInfo")
	public UserInfo getUserInfo(HttpSession session) {
		User u = (User) session.getAttribute("user");

		return userService.getUserInfo(u);

	}

}
