package com.revature.Revinsure.controllers;

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
	public boolean register(@RequestBody User user, @RequestBody UserInfo userInfo) {
		user.setInfo(userInfo);
		boolean result = userService.registerUser(user);
		
		return result;
	
	}
	
	@GetMapping(value = "/register")
	public User checkUser(@RequestBody User user) {
		User result = userService.getUserByEmail(user.getEmail());
		
		return result;
	}
	
	
}
