package com.revature.Revinsure.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.Revinsure.RevinsureApplicationTests;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserInfo;
import com.revature.Revinsure.repo.UserDao;
import com.revature.Revinsure.repo.UserInfoDao;

@RunWith(MockitoJUnitRunner.class)
public class TestUserServices extends RevinsureApplicationTests{
	
	@MockBean
	private static UserDao userDao;
	
	@MockBean
	private static UserInfoDao userInfoDao;
	
	@Autowired
	@InjectMocks
	private UserService userService = new UserServiceImpl();
	
	@Test
	public void testRegisterUser() {
		User user = new User("abc@gmail.com", "1234567");
		User userTwo =  new User();
		UserInfo userInfo = new UserInfo(1, user, "Firstname","Lastname","123 Avenue", "Buffalo", "New York", "12345");
		UserInfo userInfoTwo = new UserInfo();
				
//		user.setInfo(userInfo);
//		userTwo.setInfo(userInfoTwo);
		
		when(userDao.save(user)).thenReturn(user);
		when(userDao.save(userTwo)).thenReturn(null);
		when(userInfoDao.save(userInfo)).thenReturn(userInfo);
		when(userInfoDao.save(userInfoTwo)).thenReturn(userInfoTwo);
		
		assertTrue(userService.registerUser(user) == user);	
		assertFalse(userService.registerUser(userTwo) == null);
		
		
	}
	
	@Test
	public void testValidateUser() {
		User user = new User("abc@gmail.com", "1234567");
		User userTwo = new User("random@gmail.com", "1234567");
		
		when(userDao.findByEmail(user.getEmail())).thenReturn(user);
		when(userDao.findByEmail(userTwo.getEmail())).thenReturn(null);		
		
		assertNotNull(userService.getUserByEmail(user.getEmail()));
		assertNull(userService.getUserByEmail(userTwo.getEmail()));
	}

}
