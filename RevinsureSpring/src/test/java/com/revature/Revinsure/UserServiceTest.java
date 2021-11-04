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
import com.revature.Revinsure.models.UserType;
import com.revature.Revinsure.repo.UserDao;
import com.revature.Revinsure.repo.UserInfoDao;
import com.revature.Revinsure.services.UserService;
import com.revature.Revinsure.services.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends RevinsureApplicationTests{
	
	private static User u1 = new User(1, "newEmail@test.com", "oldPassword", null, null, null, null, null, null);
	private static User u2 = new User(-1, "newEmail2@test.com", "oldPassword2", null, null, null, null, null, null);
	private static User u3 = new User(3, "u3@test.com", "newPassword", null, null, null, null, null, null);
	private static User u4 = new User(-2, "u3@test.com", "newPassword2", null, null, null, null, null, null);
	private static User u5 = new User(5, null, null, null, null, null, null, null, null);
	private static User u6 = new User(6, null, "newPassword2", null, null, null, null, null, null);
	private static User u7 = new User(7, "u3@test.com", null, null, null, null, null, null, null);
	
	
	@MockBean
	private static UserDao uDao;
	
	@MockBean
	private static UserInfoDao uiDao;
	
	@Autowired
	@InjectMocks
	private UserService uService = new UserServiceImpl();
	
	@BeforeClass
	public static void mockDao() {
		
		MockitoAnnotations.openMocks(uDao);
		MockitoAnnotations.openMocks(uiDao);
		
	}
	
	@Test
	public void testUpdateEmail() {
		
		when(uDao.updateUser(u1.getEmail(), u1.getPassword(), u1.getId())).thenReturn(true);
		when(uDao.updateUser(u2.getEmail(), u2.getPassword(), u2.getId())).thenReturn(false);
		when(uDao.updateUser(u5.getEmail(), u5.getPassword(), u5.getId())).thenReturn(false);
		when(uDao.updateUser(u6.getEmail(), u6.getPassword(), u6.getId())).thenReturn(false);
		
		assertTrue(uService.updateEmail(u1, u1.getEmail()));
		assertFalse(uService.updateEmail(u2, u2.getEmail()));
		assertFalse(uService.updateEmail(u5, u5.getEmail()));
		assertFalse(uService.updateEmail(u6, u6.getEmail()));
		
	}
	
	@Test
	public void testUpdatePassword() {
		
		when(uDao.updateUser(u3.getEmail(), u3.getPassword(), u3.getId())).thenReturn(true);
		when(uDao.updateUser(u4.getEmail(), u4.getPassword(), u4.getId())).thenReturn(false);
		when(uDao.updateUser(u5.getEmail(), u5.getPassword(), u5.getId())).thenReturn(false);
		when(uDao.updateUser(u7.getEmail(), u7.getPassword(), u7.getId())).thenReturn(false);
		
		assertTrue(uService.updatePassword(u3, u3.getPassword()));
		assertFalse(uService.updatePassword(u4, u4.getPassword()));
		assertFalse(uService.updatePassword(u5, u5.getEmail()));
		assertFalse(uService.updatePassword(u7, u7.getEmail()));
		
	}
	
	@Test
	public void testUpdateUserInfo() {
		
		when(uiDao.updateInfo()).thenReturn(true);
		when(uiDao.updateInfo()).thenReturn(false);
		when(uiDao.updateInfo()).thenReturn(false);
		when(uiDao.updateInfo()).thenReturn(false);
		
		assertTrue(uService.updateUserInfo(u3, u3.getInfo()));
		assertFalse(uService.updateUserInfo(u4, u4.getInfo()));
		assertFalse(uService.updateUserInfo(u5, u5.getInfo()));
		assertFalse(uService.updateUserInfo(u7, u7.getInfo()));
		
	}
	
	
	
	


}
