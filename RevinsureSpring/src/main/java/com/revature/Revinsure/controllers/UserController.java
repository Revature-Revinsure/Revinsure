package com.revature.Revinsure.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserInfo;
import com.revature.Revinsure.services.UserService;

@RestController
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
	
	
}
