package com.revature.Revinsure;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserInfo;
import com.revature.Revinsure.models.UserType;
import com.revature.Revinsure.repo.UserDao;
import com.revature.Revinsure.repo.UserInfoDao;
import com.revature.Revinsure.services.UserService;
import com.revature.Revinsure.services.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends RevinsureApplicationTests{
	
	private static User u1 = new User(1, "oldEmail@test.com", "oldPassword", null, null, null, null);
	private static User u2 = new User(-1, "newEmail2@test.com", "oldPassword2", null, null, null, null);
	private static User u3 = new User(3, "u3@test.com", "newPassword", null, null, null, null);
	private static User u4 = new User(-2, "u3@test.com", "newPassword2", null, null, null, null);
	private static User u5 = new User(5, null, null, null, null, null, null);
	private static User u6 = new User(6, null, "newPassword2", null, null, null, null);
	private static User u7 = new User(7, "u3@test.com", null, null, null, null, null);
	
	private static UserInfo ui1 = new UserInfo(-1, u1, "firstname1", "lastname1", "1 Street", "Testington", "Test State", "12345");
	private static UserInfo ui2 = new UserInfo(-1, u2, null, "lastname2", "1 Street", "Testington", "Test State", "12345");
	private static UserInfo ui3 = new UserInfo(-1, u3, "firstname3", null, "1 Street", "Testington", "Test State", "12345");
	private static UserInfo ui4 = new UserInfo(-1, u4, "firstname4", "lastname4", null, "Testington", "Test State", "12345");
	
	
	@MockBean
	private static UserDao userDao;
	
	@MockBean
	private static UserInfoDao userInfoDao;
	
	@Autowired
	@InjectMocks
	private UserService uService = new UserServiceImpl(userDao);
	
	@BeforeClass
	public static void mockDao() {
		
		MockitoAnnotations.openMocks(userDao);
		MockitoAnnotations.openMocks(userInfoDao);
		
	}
	
	@Test
	public void testUpdateEmail() {
		
		when(userDao.updateUser("newEmail@test.com", u1.getPassword(), u1.getId())).thenReturn(true);
		when(userDao.updateUser(u2.getEmail(), u2.getPassword(), u2.getId())).thenReturn(false);
		when(userDao.updateUser(u5.getEmail(), u5.getPassword(), u5.getId())).thenReturn(false);
		when(userDao.updateUser(u6.getEmail(), u6.getPassword(), u6.getId())).thenReturn(false);
		
		assertTrue(uService.updateEmail(u1, "newEmail@test.com"));
		assertFalse(uService.updateEmail(u2, u2.getEmail()));
		assertFalse(uService.updateEmail(u5, u5.getEmail()));
		assertFalse(uService.updateEmail(u6, u6.getEmail()));
		
	}
	
	@Test
	public void testUpdatePassword() {
		
		when(userDao.updateUser(u3.getEmail(), u3.getPassword(), u3.getId())).thenReturn(true);
		when(userDao.updateUser(u4.getEmail(), u4.getPassword(), u4.getId())).thenReturn(false);
		when(userDao.updateUser(u5.getEmail(), u5.getPassword(), u5.getId())).thenReturn(false);
		when(userDao.updateUser(u7.getEmail(), u7.getPassword(), u7.getId())).thenReturn(false);
		
		assertTrue(uService.updatePassword(u3, u3.getPassword()));
		assertFalse(uService.updatePassword(u4, u4.getPassword()));
		assertFalse(uService.updatePassword(u5, u5.getEmail()));
		assertFalse(uService.updatePassword(u7, u7.getEmail()));
		
	}
	
	@Test
	public void testUpdateUserInfo() {
		
		when(userInfoDao.updateInfo(ui1.getFirstName(), ui1.getLastName(), ui1.getAddress(), ui1.getCity(), ui1.getState(), ui1.getZip(), u1.getId())).thenReturn(true);
		when(userInfoDao.updateInfo(ui2.getFirstName(), ui2.getLastName(), ui2.getAddress(), ui2.getCity(), ui2.getState(), ui2.getZip(), u2.getId())).thenReturn(false);
		when(userInfoDao.updateInfo(ui3.getFirstName(), ui3.getLastName(), ui3.getAddress(), ui3.getCity(), ui3.getState(), ui3.getZip(), u3.getId())).thenReturn(false);
		when(userInfoDao.updateInfo(ui4.getFirstName(), ui4.getLastName(), ui4.getAddress(), ui4.getCity(), ui4.getState(), ui4.getZip(), u4.getId())).thenReturn(false);
		
		assertTrue(uService.updateUserInfo(u1, ui1));
		assertFalse(uService.updateUserInfo(u2, ui2));
		assertFalse(uService.updateUserInfo(u3, ui3));
		assertFalse(uService.updateUserInfo(u4, ui4));
		
	}
	
	
	
	


}
