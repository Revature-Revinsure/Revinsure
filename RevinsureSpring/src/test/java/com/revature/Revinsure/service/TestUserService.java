package com.revature.Revinsure.service;

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
	private UserDao userDao;
	
	private static User fakeUser = new User(-1,"fake@gmail.com","fake", UserType.PATIENT);     
	private static User realUser = new User(1,"realEmail@hotmail.com","real", UserType.EMPLOYEE);     
	private static User secondUser = new User(2,"realEmail@gmail.com","totallylegit", UserType.PATIENT);
	
	@Autowired
	@InjectMocks
	private UserService userService = new UserServiceImpl(userDao);
	
	@BeforeClass
	void setup() {
		MockitoAnnotations.openMocks(userDao);
	}
	
	@Test
	void testGetUser() {
		when(userDao.getUserByEmail("realEmail@gmail.com")).thenReturn(secondUser);
		when(userDao.getUserByEmail("realEmail@hotmail.com")).thenReturn(realUser);
		when(userDao.getUserByEmail("fake")).thenReturn(null);
		assertTrue(UserServiceImpl.getUserByEmail(realUser.email) == realUser);
	}
	
	@Test
	void testAuthenticateUser() {}
	


			
	@Test
	void authenticate() {
		List<Book> fake1 = new ArrayList<>();
		User fakeuser =  new User ("yibeltal","yib@gmail.com","pass",fake1);


}
