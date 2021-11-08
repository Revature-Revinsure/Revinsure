package com.revature.Revinsure.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserInfo;
import com.revature.Revinsure.services.UserService;
import com.revature.Revinsure.services.UserServiceImpl;

@RestController("userController")
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

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
		
		System.out.println(user);
		boolean isAuthenticated = userServiceImpl.authenticate(user);
		
		User currentUser = userServiceImpl.getUserByEmail(user.getEmail());
		
		if(isAuthenticated == true) {
			session.setAttribute("loggedInUser", currentUser);
		} else {
			System.out.println("inside"+currentUser);
			currentUser = null;
		}
		System.out.println("aaaa"+currentUser);
		
		return currentUser;
	}
		
}
