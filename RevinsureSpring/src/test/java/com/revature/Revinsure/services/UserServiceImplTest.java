package com.revature.Revinsure.services;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
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
import com.revature.Revinsure.models.UserInfo;
import com.revature.Revinsure.models.UserType;
import com.revature.Revinsure.repo.CovidQuestionDao;
import com.revature.Revinsure.repo.UserDao;
import com.revature.Revinsure.repo.UserInfoDao;
import com.revature.Revinsure.services.UserService;
import com.revature.Revinsure.services.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest extends RevinsureApplicationTests {

	@MockBean
	private static UserDao userDao;

	@MockBean
	private static UserInfoDao userInfoDao;

	@MockBean
	private static CovidQuestionDao covidDao;

	@Autowired
	@InjectMocks
	private UserService userService = new UserServiceImpl(userDao);

	private final User fakeUser = new User(-1, "fake@gmail.com", "fake", UserType.PATIENT);
	private final User realUser = new User(1, "realEmail@hotmail.com", "real", UserType.EMPLOYEE);
	private final User secondUser = new User(2, "realEmail@gmail.com", "totallylegit", UserType.PATIENT);
	private final CovidQuestion fakeCovidQuestion = new CovidQuestion(-1, null, false, false, new Date());
	private final CovidQuestion updatedFakeCovidQuestion = new CovidQuestion(94, null, false, false, new Date());
	private final CovidQuestion nullDate = new CovidQuestion(-1, null, false, false, null);

	
	private final User u1 = new User(1, "oldEmail@test.com", "oldPassword", null, null, null, null);
	private final User u2 = new User(-1, "newEmail2@test.com", "oldPassword2", null, null, null, null);
	private final User u3 = new User(3, "u3@test.com", "newPassword", null, null, null, null);
	private final User u4 = new User(-2, "u3@test.com", "newPassword2", null, null, null, null);
	private final User u5 = new User(5, null, null, null, null, null, null);
	private final User u6 = new User(6, null, "newPassword2", null, null, null, null);
	private final User u7 = new User(7, "u3@test.com", null, null, null, null, null);
	
	private final UserInfo ui1 = new UserInfo(-1, u1, "firstname1", "lastname1", "1 Street", "Testington", "Test State", "12345");
	private final UserInfo ui2 = new UserInfo(-1, u2, null, "lastname2", "1 Street", "Testington", "Test State", "12345");
	private final UserInfo ui3 = new UserInfo(-1, u3, "firstname3", null, "1 Street", "Testington", "Test State", "12345");
	private final UserInfo ui4 = new UserInfo(-1, u4, "firstname4", "lastname4", null, "Testington", "Test State", "12345");
	
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
		User existingUser = new User(78, "alreadyhere@gmail.com", "password", UserType.PATIENT);
		Date oldDate = new GregorianCalendar(2020, Calendar.FEBRUARY, 13).getTime();
		CovidQuestion existingCovidQuestion = new CovidQuestion(94, existingUser, false, false, oldDate);
		CovidQuestion updatedExistingCovidQuestion = new CovidQuestion(94, existingUser, true, true, new Date());
		when(covidDao.findByUser(fakeUser)).thenReturn(null);
		when(covidDao.save(fakeCovidQuestion)).thenReturn(updatedFakeCovidQuestion);
		when(covidDao.save(nullDate)).thenReturn(nullDate);
		when(covidDao.findByUser(existingUser)).thenReturn(existingCovidQuestion);

		assertTrue(userService.createOrUpdateCovidForm(fakeUser, fakeCovidQuestion));
		assertTrue(userService.createOrUpdateCovidForm(existingUser, updatedExistingCovidQuestion));
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
	
	
	@Test
	public void testRegisterUser() {
		User user = new User(1, "abc@gmail.com", "1234567", UserType.PATIENT);
		User userTwo = new User(-1, null, null, null, null, null, null);
		User userThree = new User(-1, null, "1234567", UserType.EMPLOYEE);
		User userFour = new User(-1, "abc@gmail.com", null, UserType.PATIENT);

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
		User user = new User(-1, "abc@gmail.com", "1234567", UserType.PATIENT);
		User userTwo = new User();
		// User userThree = new User("abc@gmail.com", "1234567");
		// User userFour = new User("abc@gmail.com", "1234567");
		UserInfo userInfo = new UserInfo(1, user, "Firstname", "Lastname", "123 Avenue", "Buffalo", "New York",
				"12345");
		UserInfo userInfoTwo = new UserInfo(-1, user, null, null, null, null, null, null);
		UserInfo userInfoThree = new UserInfo(1, userTwo, "Firstname", "Lastname", "123 Avenue", "Buffalo", "New York",
				"12345");
		UserInfo userInfoFour = new UserInfo(1, user, null, "Lastname", "123 Avenue", "Buffalo", "New York", "12345");
		UserInfo userInfoFive = new UserInfo(1, user, "Firstname", null, "123 Avenue", "Buffalo", "New York", "12345");
		UserInfo userInfoSix = new UserInfo(1, user, "Firstname", "Lastname", null, "Buffalo", "New York", "12345");
		UserInfo userInfoSeven = new UserInfo(1, user, "Firstname", "Lastname", "123 Avenue", null, "New York",
				"12345");
		UserInfo userInfoEight = new UserInfo(1, user, "Firstname", "Lastname", "123 Avenue", "Buffalo", null, "12345");
		UserInfo userInfoNine = new UserInfo(1, user, "Firstname", "Lastname", "123 Avenue", "Buffalo", "New York",
				null);

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
		User user = new User(-1, "abc@gmail.com", "1234567", UserType.PATIENT);
		User userTwo = new User(-1, "randomEmail@gmail.com", "1234567", UserType.EMPLOYEE);

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

		when(userDao.updatePasswordByEmail(testUser1.getPassword(), testUser1.getEmail())).thenReturn(1);
		when(userDao.updatePasswordByEmail(testUser2.getPassword(), testUser2.getEmail())).thenReturn(0);
		when(userDao.updatePasswordByEmail(testUser3.getPassword(), testUser3.getEmail())).thenReturn(0);
		when(userDao.updatePasswordByEmail(testUser4.getPassword(), testUser4.getEmail())).thenReturn(0);
		when(userDao.updatePasswordByEmail(testUser5.getPassword(), testUser5.getEmail())).thenReturn(0);
		when(userDao.updatePasswordByEmail(testUser6.getPassword(), testUser6.getEmail())).thenReturn(0);

		assertTrue(userService.updatePasswordByEmail(testUser1));
		assertFalse(userService.updatePasswordByEmail(testUser2));
		assertFalse(userService.updatePasswordByEmail(testUser3));
		assertFalse(userService.updatePasswordByEmail(testUser4));
		assertFalse(userService.updatePasswordByEmail(testUser5));
		assertFalse(userService.updatePasswordByEmail(testUser6));

	}
	
	
	@Test
	public void testUpdateEmail() {
		
		when(userDao.updateUsername("newEmail@test.com", u1.getId())).thenReturn(1);
		when(userDao.updateUsername(u2.getEmail(), u2.getId())).thenReturn(0);
		when(userDao.updateUsername(u5.getEmail(), u5.getId())).thenReturn(0);
		when(userDao.updateUsername(u6.getEmail(), u6.getId())).thenReturn(0);
		
		assertTrue(userService.updateEmail(u1, "newEmail@test.com"));
		assertFalse(userService.updateEmail(u2, u2.getEmail()));
		assertFalse(userService.updateEmail(u5, u5.getEmail()));
		assertFalse(userService.updateEmail(u6, u6.getEmail()));
		
	}
	
	@Test
	public void testUpdatePassword() {
		
		when(userDao.updatePasswordByEmail(u3.getPassword(), u3.getEmail())).thenReturn(1);
		when(userDao.updatePasswordByEmail(u4.getPassword(), u4.getEmail())).thenReturn(0);
		when(userDao.updatePasswordByEmail(u5.getPassword(), u5.getEmail())).thenReturn(0);
		when(userDao.updatePasswordByEmail(u7.getPassword(), u7.getEmail())).thenReturn(0);
		
		assertTrue(userService.updatePassword(u3, u3.getPassword()));
		assertFalse(userService.updatePassword(u4, u4.getPassword()));
		assertFalse(userService.updatePassword(u5, u5.getEmail()));
		assertFalse(userService.updatePassword(u7, u7.getEmail()));
		
	}
	
	@Test
	public void testUpdateUserInfo() {
		
		when(userInfoDao.updateInfo(ui1.getFirstName(), ui1.getLastName(), ui1.getAddress(), ui1.getCity(), ui1.getState(), ui1.getZip(), u1.getId())).thenReturn(1);
		when(userInfoDao.updateInfo(ui2.getFirstName(), ui2.getLastName(), ui2.getAddress(), ui2.getCity(), ui2.getState(), ui2.getZip(), u2.getId())).thenReturn(0);
		when(userInfoDao.updateInfo(ui3.getFirstName(), ui3.getLastName(), ui3.getAddress(), ui3.getCity(), ui3.getState(), ui3.getZip(), u3.getId())).thenReturn(0);
		when(userInfoDao.updateInfo(ui4.getFirstName(), ui4.getLastName(), ui4.getAddress(), ui4.getCity(), ui4.getState(), ui4.getZip(), u4.getId())).thenReturn(0);
		
		assertTrue(userService.updateUserInfo(u1, ui1));
		assertFalse(userService.updateUserInfo(u2, ui2));
		assertFalse(userService.updateUserInfo(u3, ui3));
		assertFalse(userService.updateUserInfo(u4, ui4));
		
	}
}
