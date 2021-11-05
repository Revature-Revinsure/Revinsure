package com.revature.Revinsure.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserInfo;
import com.revature.Revinsure.services.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
public class UserController {
	
	@Autowired 
	private UserService uService;
	
	public User getUserSession(HttpSession session) {
		
		User u = null;

		if(session.getAttribute("user") == null) {
			
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
		
		if(u.getId() == -1) {
			
			response.setStatus(400);
			
		}else {
			
			uService.updatePassword(u, password);
			response.setStatus(200);
			
		}


	}
	
	@PostMapping(value = "/updateEmail")
	public void updateEmail(@RequestBody String email, HttpServletResponse response, HttpSession session) {
		
		User u = this.getUserSession(session);
		
		if(u.getId() == -1) {
			
			response.setStatus(400);
			
		}else {
			
			uService.updateEmail(u, email);
			response.setStatus(200);
			
		}


	}
	
	@PostMapping(value = "/updateUserInfo")
	public void updateUserInfo(@RequestBody UserInfo userInfo, HttpServletResponse response, HttpSession session) {
		
		User u = this.getUserSession(session);
		
		if(u.getId() == -1) {
			
			response.setStatus(400);
			
		}else {
			
			uService.updateUserInfo(u, userInfo);
			response.setStatus(200);
			
		}


	}

	
	
}
