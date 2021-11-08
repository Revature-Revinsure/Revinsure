package com.revature.Revinsure.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;


import com.revature.Revinsure.RevinsureApplicationTests;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserType;
import com.revature.Revinsure.repo.UserDao;
import com.revature.Revinsure.services.UserService;
import com.revature.Revinsure.services.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends RevinsureApplicationTests {
	
	@MockBean
	private static UserDao userDao;
	
	private static User fakeUser = new User(-1,"fake@gmail.com","fake", UserType.PATIENT);     
	private static User realUser = new User(1,"realEmail@hotmail.com","real", UserType.EMPLOYEE);     
	private static User secondUser = new User(2,"realEmail@gmail.com","totallylegit", UserType.PATIENT);
	
	@Autowired
	@InjectMocks
	public final UserService userService = new UserServiceImpl(userDao);
	
	@BeforeClass
	void setup() {
		MockitoAnnotations.openMocks(userDao);
	}
	
	@Test
	public void testGetUser() {
		when(userDao.getUserByEmail("realEmail@gmail.com")).thenReturn(secondUser);
		when(userDao.getUserByEmail("realEmail@hotmail.com")).thenReturn(realUser);
		when(userDao.getUserByEmail("fake")).thenReturn(null);
		
		assertEquals(userService.getUserByEmail(realUser.getEmail()), realUser);
		
		assertEquals(userService.getUserByEmail(secondUser.getEmail()), secondUser);
		
		assertNull(userService.getUserByEmail(fakeUser.getEmail()));
		
		assertNotEquals(userService.getUserByEmail(secondUser.getEmail()), realUser);
		
		assertNotEquals(userService.getUserByEmail(realUser.getEmail()), secondUser);
		
		assertNotEquals(userService.getUserByEmail(fakeUser.getEmail()), realUser);
		
		assertNotEquals(userService.getUserByEmail(secondUser.getEmail()), null);
		
		assertNotEquals(userService.getUserByEmail(realUser.getEmail()), null);
		
		assertNull(userService.getUserByEmail(null));
	}
	
	@Test
	public void testAuthenticateUser() {
		when(userDao.getUserByEmail("realEmail@gmail.com")).thenReturn(secondUser);
		when(userDao.getUserByEmail("realEmail@hotmail.com")).thenReturn(realUser);
		assertTrue(userService.authenticate(realUser));
		assertTrue(userService.authenticate(secondUser));
	}
}
