package com.revature.Revinsure.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Test;

import com.revature.Revinsure.RevinsureApplicationTests;
import com.revature.Revinsure.models.CovidQuestion;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserType;
import com.revature.Revinsure.repo.CovidQuestionDao;
import com.revature.Revinsure.services.UserService;
import com.revature.Revinsure.services.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends RevinsureApplicationTests {
	
	@MockBean
	private static CovidQuestionDao covidDao;
	
	@Autowired
	@InjectMocks
	private UserService userService = new UserServiceImpl();
	
	private final User fakeUser = new User(-1, "fake@email.com", "password", UserType.PATIENT, null, null, null, null, null);
	private final CovidQuestion fakeCovidQuestion = new CovidQuestion(-1, null, false, false, new Date());
	private final CovidQuestion updatedFakeCovidQuestion = new CovidQuestion(94, null, false, false, new Date());
	private final CovidQuestion nullDate = new CovidQuestion(-1, null, false, false, null);
	
	@BeforeClass
	public static void fakeMyDao() {
		MockitoAnnotations.openMocks(covidDao);
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
