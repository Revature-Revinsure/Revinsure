package com.revature.Revinsure.services;

import static org.junit.Assert.assertEquals;
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
import com.revature.Revinsure.models.UserType;
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
	private UserService userService = new UserServiceImpl(userDao);
	
	@Test
	public void testRegisterUser() {
		User user = new User(1,"abc@gmail.com", "1234567",UserType.PATIENT);
		User userTwo =  new User(-1, null, null, null, null, null, null);
		User userThree = new User(-1,null, "1234567",UserType.EMPLOYEE);
		User userFour = new User(-1,"abc@gmail.com", null, UserType.PATIENT);
			
		when(userDao.save(user)).thenReturn(user);
		when(userDao.save(userTwo)).thenReturn(null);
		when(userDao.save(userThree)).thenReturn(null);
		when(userDao.save(userFour)).thenReturn(null);
				
		assertNotNull(userService.registerUser(user));	
		assertEquals(user, userService.registerUser(user));
		assertNull(userService.registerUser(userTwo));
		assertNull(userService.registerUser(userThree));
		assertNull(userService.registerUser(userFour));
				
	}
	
	@Test
	public void testRegisterUserInfo() {
		User user = new User(-1,"abc@gmail.com", "1234567",UserType.PATIENT);
		User userTwo =  new User();
		//User userThree = new User("abc@gmail.com", "1234567");
		//User userFour = new User("abc@gmail.com", "1234567");
		UserInfo userInfo = new UserInfo(1, user, "Firstname","Lastname","123 Avenue", "Buffalo", "New York", "12345");
		UserInfo userInfoTwo = new UserInfo(-1, user, null, null, null, null, null, null);	
		UserInfo userInfoThree = new UserInfo(1, userTwo, "Firstname","Lastname","123 Avenue", "Buffalo", "New York", "12345");
		UserInfo userInfoFour = new UserInfo(1, user, null,"Lastname","123 Avenue", "Buffalo", "New York", "12345");
		UserInfo userInfoFive = new UserInfo(1, user, "Firstname",null,"123 Avenue", "Buffalo", "New York", "12345");
		UserInfo userInfoSix = new UserInfo(1, user, "Firstname","Lastname",null, "Buffalo", "New York", "12345");
		UserInfo userInfoSeven = new UserInfo(1, user, "Firstname","Lastname","123 Avenue", null, "New York", "12345");
		UserInfo userInfoEight = new UserInfo(1, user, "Firstname","Lastname","123 Avenue", "Buffalo", null, "12345");
		UserInfo userInfoNine = new UserInfo(1, user, "Firstname","Lastname","123 Avenue", "Buffalo", "New York", null);
		
		when(userInfoDao.save(userInfo)).thenReturn(userInfo);
		when(userInfoDao.save(userInfoTwo)).thenReturn(null);
		when(userInfoDao.save(userInfoThree)).thenReturn(null);
		when(userInfoDao.save(userInfoFour)).thenReturn(null);
		when(userInfoDao.save(userInfoFive)).thenReturn(null);
		when(userInfoDao.save(userInfoSix)).thenReturn(null);
		when(userInfoDao.save(userInfoSeven)).thenReturn(null);
		when(userInfoDao.save(userInfoEight)).thenReturn(null);
		when(userInfoDao.save(userInfoNine)).thenReturn(null);
		
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
		User user = new User(-1,"abc@gmail.com", "1234567",UserType.PATIENT);
		User userTwo = new User(-1,"randomEmail@gmail.com", "1234567",UserType.EMPLOYEE);
		
		when(userDao.getUserByEmail(user.getEmail())).thenReturn(user);
		when(userDao.getUserByEmail(userTwo.getEmail())).thenReturn(null);		
		
		assertEquals(user, userService.getUserByEmail(user.getEmail()));
		assertNull(userService.getUserByEmail(userTwo.getEmail()));
	}
	
	
	@Test
	public void testChangePassword() {
		User testUser1 = new User(-1, "test1@email.com", "fakepass", UserType.PATIENT);
		User testUser2 = new User(-1, "test2@email.com", "", UserType.EMPLOYEE);
		User testUser3 = new User(-1, "", "", null);
		User testUser4 = new User(-1, "", "fakepass", null);
		User testUser5 = new User();
		User testUser6 = new User(-1, "unregistered@email.com", "fakepass", null);
		
		
		when(userDao.updatePassword(testUser1.getPassword(), testUser1.getEmail())).thenReturn(true);
		when(userDao.updatePassword(testUser2.getPassword(), testUser2.getEmail())).thenReturn(false);
		when(userDao.updatePassword(testUser3.getPassword(), testUser3.getEmail())).thenReturn(false);
		when(userDao.updatePassword(testUser4.getPassword(), testUser4.getEmail())).thenReturn(false);
		when(userDao.updatePassword(testUser5.getPassword(), testUser5.getEmail())).thenReturn(false);
		when(userDao.updatePassword(testUser6.getPassword(), testUser6.getEmail())).thenReturn(false);
		
		
		assertTrue(userService.updatePasswordByEmail(testUser1));
		assertFalse(userService.updatePasswordByEmail(testUser2));
		assertFalse(userService.updatePasswordByEmail(testUser3));
		assertFalse(userService.updatePasswordByEmail(testUser4));
		assertFalse(userService.updatePasswordByEmail(testUser5));
		assertFalse(userService.updatePasswordByEmail(testUser6));
		
	}
	
}
