package com.revature.Revinsure.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Revinsure.models.User;
import com.revature.Revinsure.services.UserServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping(value = "/login")
	public User login(HttpSession session, @RequestBody User user) {
		
		boolean isAuthenticated = userServiceImpl.authenticate(user);
		
		User currentUser = userServiceImpl.getUserByEmail(user.getEmail());
		
		if(isAuthenticated == true) {
			session.setAttribute("loggedInUser", currentUser);
		} else {
			currentUser = null;
		}
		
		return currentUser;
	}
		
}
