package com.revature.Revinsure.service;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.MockitoAnnotations;

import com.revature.Revinsure.RevinsureApplicationTests;
import com.revature.Revinsure.models.CovidQuestion;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserType;
import com.revature.Revinsure.repo.CovidQuestionDao;
import com.revature.Revinsure.repo.UserDao;
import com.revature.Revinsure.repo.UserInfoDao;
import com.revature.Revinsure.services.UserService;
import com.revature.Revinsure.services.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends RevinsureApplicationTests {
	
	@MockBean
	private static UserDao userDao;
	
	@MockBean
	private static UserInfoDao userInfoDao;
	
	@MockBean
	private static CovidQuestionDao covidDao;
	
	@Autowired
	@InjectMocks
	private UserService userService = new UserServiceImpl(userDao);
	
	
	private static User fakeUser = new User(-1,"fake@gmail.com","fake", UserType.PATIENT);     
	private static User realUser = new User(1,"realEmail@hotmail.com","real", UserType.EMPLOYEE);     
	private static User secondUser = new User(2,"realEmail@gmail.com","totallylegit", UserType.PATIENT);
	private final User fakeCovidUser = new User(-1, "fake@email.com", "password", UserType.PATIENT);
	private final CovidQuestion fakeCovidQuestion = new CovidQuestion(-1, null, false, false, new Date());
	private final CovidQuestion updatedFakeCovidQuestion = new CovidQuestion(94, null, false, false, new Date());
	private final CovidQuestion nullDate = new CovidQuestion(-1, null, false, false, null);
	
	@BeforeClass
	public static void fakeMyDao() {
		MockitoAnnotations.openMocks(covidDao);
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
	
	@Test
	public void testCreateCovidForm(User user, CovidQuestion covidform) {
		when(covidDao.save(fakeCovidQuestion)).thenReturn(updatedFakeCovidQuestion);
		when(covidDao.save(nullDate)).thenReturn(nullDate);
		
		assertTrue(userService.createCovidForm(fakeUser, fakeCovidQuestion));
		assertFalse(userService.createCovidForm(fakeUser, null));
		assertFalse(userService.createCovidForm(fakeUser, nullDate));
	}
	
	@Test
	public void testUpdateCovidForm(User user, CovidQuestion covidform) {
		
	}
}
