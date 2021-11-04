package com.revature.Revinsure.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserType;
import com.revature.Revinsure.repo.UserDao;
import com.revature.Revinsure.services.UserService;
import com.revature.Revinsure.services.UserServiceImpl;

public class TestUserService {
	
	@MockBean
	private static UserDao userDao;
	
	private static User fakeUser = new User(-1,"fake@gmail.com","fake", UserType.PATIENT);     
	private static User realUser = new User(1,"realEmail@hotmail.com","real", UserType.EMPLOYEE);     
	private static User secondUser = new User(2,"realEmail@gmail.com","totallylegit", UserType.PATIENT);
	
	@Autowired
	@InjectMocks
	public static UserService userService = new UserServiceImpl(userDao);
	
	@BeforeClass
	void setup() {
		MockitoAnnotations.openMocks(userDao);
	}
	
	@Test
	public static void testGetUser() {
		when(userDao.getUserByEmail("realEmail@gmail.com")).thenReturn(secondUser);
		when(userDao.getUserByEmail("realEmail@hotmail.com")).thenReturn(realUser);
		when(userDao.getUserByEmail("fake")).thenReturn(null);
		assertTrue(userService.getUserByEmail(realUser.getEmail()) == realUser);
		assertTrue(userService.getUserByEmail(secondUser.getEmail()) == secondUser);
		assertTrue(userService.getUserByEmail(fakeUser.getEmail()) == null);
		assertFalse(userService.getUserByEmail(realUser.getEmail()) != realUser);
		assertFalse(userService.getUserByEmail(secondUser.getEmail()) != secondUser);
		assertFalse(userService.getUserByEmail(fakeUser.getEmail()) != null);
	}
	
	@Test
	public static void testAuthenticateUser() {
		when(userDao.getUserByEmail("realEmail@gmail.com")).thenReturn(secondUser);
		when(userDao.getUserByEmail("realEmail@hotmail.com")).thenReturn(realUser);
		when(userDao.getUserByEmail("fake")).thenReturn(null);
		assertTrue(userService.authenticate(realUser));
		assertTrue(userService.authenticate(secondUser));
		assertFalse(userService.authenticate(fakeUser));
	}
}
