package com.revature.Revinsure.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Revinsure.models.User;
import com.revature.Revinsure.services.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
public class UserController {

	@Autowired
	private UserService userService;
	
	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	@PutMapping(value = "/updatePassword")
	public boolean changePasswordAfterLogin(HttpSession session, @RequestBody User user) {
		
		User oldUser = (User)session.getAttribute("user");
		boolean result = userService.updatePassword(oldUser, user.getPassword());
		
		return result;

	}
	
	@PutMapping(value = "/updatePasswordByEmail")
	public boolean changePasswordBeforeLogin(@RequestBody User user) {
		
		boolean result = userService.updatePasswordByEmail(user);
		
		return result;

	}
	
}
