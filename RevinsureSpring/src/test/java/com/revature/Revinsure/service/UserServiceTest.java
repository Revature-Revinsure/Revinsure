package com.revature.Revinsure.service;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
	
	
	private final User fakeUser = new User(-1,"fake@gmail.com","fake", UserType.PATIENT);     
	private final User realUser = new User(1,"realEmail@hotmail.com","real", UserType.EMPLOYEE);     
	private final User secondUser = new User(2,"realEmail@gmail.com","totallylegit", UserType.PATIENT);
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
	public void createOrUpdateCovidForm() {
		when(covidDao.save(fakeCovidQuestion)).thenReturn(updatedFakeCovidQuestion);
		when(covidDao.save(nullDate)).thenReturn(nullDate);
		
		assertTrue(userService.createOrUpdateCovidForm(fakeUser, fakeCovidQuestion));
		assertFalse(userService.createOrUpdateCovidForm(fakeUser, null));
		assertFalse(userService.createOrUpdateCovidForm(fakeUser, nullDate));
	}
	
	@Test
	public void checkIfAfterFourteenDays() {
		Date oldDate = new GregorianCalendar(2020, Calendar.FEBRUARY, 13).getTime();
		CovidQuestion oldCovidQuestion = new CovidQuestion(-1, null, false, false, oldDate);
		
		when(covidDao.findByUser(fakeUser)).thenReturn(oldCovidQuestion);
		when(covidDao.findByUser(realUser)).thenReturn(fakeCovidQuestion);
		when(covidDao.findByUser(secondUser)).thenReturn(nullDate);
		
		assertTrue(userService.checkIfAfterFourteenDays(fakeUser));
		assertTrue(userService.checkIfAfterFourteenDays(secondUser));
		assertFalse(userService.checkIfAfterFourteenDays(realUser));
	}
}
