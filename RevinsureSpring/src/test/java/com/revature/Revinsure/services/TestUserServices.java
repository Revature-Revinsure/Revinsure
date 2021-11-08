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
		User userThree = new User(null, "1234567");
		User userFour = new User("abc@gmail.com", null);
			
		when(userDao.save(user)).thenReturn(user);
		when(userDao.save(userTwo)).thenReturn(null);
		when(userDao.save(userThree)).thenReturn(null);
		when(userDao.save(userFour)).thenReturn(null);
				
		assertTrue(userService.registerUser(user) == user);	
		assertFalse(userService.registerUser(userTwo) == null);
		assertFalse(userService.registerUser(userThree) == null);
		assertFalse(userService.registerUser(userFour) == null);
				
	}
	
	@Test
	public void testRegisterUserInfo() {
		User user = new User("abc@gmail.com", "1234567");
		User userTwo =  new User();
		//User userThree = new User("abc@gmail.com", "1234567");
		//User userFour = new User("abc@gmail.com", "1234567");
		UserInfo userInfo = new UserInfo(1, user, "Firstname","Lastname","123 Avenue", "Buffalo", "New York", "12345");
		UserInfo userInfoTwo = new UserInfo();	
		UserInfo userInfoThree = new UserInfo(1, null, "Firstname","Lastname","123 Avenue", "Buffalo", "New York", "12345");
		UserInfo userInfoFour = new UserInfo(1, user, null,"Lastname","123 Avenue", "Buffalo", "New York", "12345");
		UserInfo userInfoFive = new UserInfo(1, user, "Firstname",null,"123 Avenue", "Buffalo", "New York", "12345");
		UserInfo userInfoSix = new UserInfo(1, user, "Firstname","Lastname",null, "Buffalo", "New York", "12345");
		UserInfo userInfoSeven = new UserInfo(1, user, "Firstname","Lastname","123 Avenue", null, "New York", "12345");
		UserInfo userInfoEight = new UserInfo(1, user, "Firstname","Lastname","123 Avenue", "Buffalo", null, "12345");
		UserInfo userInfoNine = new UserInfo(1, user, "Firstname","Lastname","123 Avenue", "Buffalo", "New York", null);
		
		when(userInfoDao.save(userInfo)).thenReturn(userInfo);
		when(userInfoDao.save(userInfoTwo)).thenReturn(userInfoTwo);
		when(userInfoDao.save(userInfoThree)).thenReturn(userInfoThree);
		when(userInfoDao.save(userInfoFour)).thenReturn(userInfoFour);
		when(userInfoDao.save(userInfoFive)).thenReturn(userInfoFive);
		when(userInfoDao.save(userInfoSix)).thenReturn(userInfoSix);
		when(userInfoDao.save(userInfoSeven)).thenReturn(userInfoSeven);
		when(userInfoDao.save(userInfoEight)).thenReturn(userInfoEight);
		when(userInfoDao.save(userInfoNine)).thenReturn(userInfoNine);
		
		assertTrue(userService.registerUserInfo(userInfo));
		assertFalse(userService.registerUserInfo(userInfoTwo));		
		assertFalse(userService.registerUserInfo(userInfoThree));	
		assertFalse(userService.registerUserInfo(userInfoFour));	
		assertFalse(userService.registerUserInfo(userInfoFive));	
		assertFalse(userService.registerUserInfo(userInfoSix));	
		assertFalse(userService.registerUserInfo(userInfoSeven));	
		assertFalse(userService.registerUserInfo(userInfoEight));	
		assertFalse(userService.registerUserInfo(userInfoNine));	
	}
	
	@Test
	public void testValidateUser() {
		User user = new User("abc@gmail.com", "1234567");
		User userTwo = new User("random@gmail.com", "1234567");
		
		when(userDao.findByEmail(user.getEmail())).thenReturn(user);
		when(userDao.findByEmail(userTwo.getEmail())).thenReturn(null);		
		
		assertTrue(userService.getUserByEmail(user.getEmail()).equals(user));
		assertFalse(userService.getUserByEmail(userTwo.getEmail()) != null);
	}

}
