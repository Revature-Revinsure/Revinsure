package com.revature.Revinsure.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
public class UserController {

	@Autowired
	private UserService userService;
	
	public UserController() {
		
	}


	@PostMapping(value = "/register")
	public User register(HttpSession session, @RequestBody User user) {
//		user.setInfo(userInfo);
		System.out.println(user);
		user = userService.registerUser(user);
		session.setAttribute("user", user);
		return user;
	
	}
	@PostMapping(value = "/registerInfo")
	public boolean register(HttpSession session, @RequestBody UserInfo userInfo) {
//		user.setInfo(userInfo);
		
		
		userInfo.setUser((User) session.getAttribute("user"));
		System.out.println(userInfo);
		boolean result = userService.registerUserInfo(userInfo);
		
		return result;
	
	}

	
	@PostMapping(value = "/register/check")
	public boolean checkUser(@RequestBody String email) {
		User result = userService.getUserByEmail(email);
		if(result == null) {
			return false;

		}
		return true;

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
		boolean isAfterFourteenDays = false;
		User testUser = new User(1,"test@gmail.com","password", UserType.PATIENT);
		
		userService.checkIfAfterFourteenDays(testUser);
		
		return isAfterFourteenDays;
	}
	
	@PostMapping(value = "/covid")
	public Message createOrUpdateCovidAnswer(HttpSession session, CovidQuestion covidQuestion) {
		Message message = new Message();
		User user = (User) session.getAttribute("user");
		
		if(userService.createOrUpdateCovidForm(user, covidQuestion)) {
			message.setMessage("Successfully added or updated Covid Questionnaire.");
		} else {
			message.setMessage("Adding or updating Covid Questionnaire failed.");
		}
		
		return message;
	}
		
}
