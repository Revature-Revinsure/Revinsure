package com.revature.Revinsure.controllers;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserInfo;
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

		}

	}

	@PostMapping(value = "/updateEmail")
	public void updateEmail(@RequestBody String email, HttpServletResponse response, HttpSession session) {

		User u = this.getUserSession(session);

		if (u.getId() == -1) {

			response.setStatus(400);

		} else {

			userService.updateEmail(u, email);
			response.setStatus(200);

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

		}
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
		if (result == null) {
			return false;

		}
		return true;

	}

	@PostMapping(value = "/login")
	public User login(HttpSession session, @RequestBody User user) {

		System.out.println(user);

		User currentUser = userService.getUserByEmail(user.getEmail());

		if (userService.authenticate(user)) {
			session.setAttribute("user", currentUser);
		} else {
			currentUser = null;
		}

		return currentUser;
	}

	@GetMapping(value = "/userInfo")
	public UserInfo getUserInfo(HttpSession session) {
		User u = (User) session.getAttribute("user");

		return userService.getUserInfo(u);

	}

}
