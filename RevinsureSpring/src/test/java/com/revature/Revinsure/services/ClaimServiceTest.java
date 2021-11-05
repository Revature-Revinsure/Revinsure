package com.revature.Revinsure.services;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;

import com.revature.Revinsure.RevinsureApplicationTests;
import com.revature.Revinsure.models.Claim;
import com.revature.Revinsure.models.Status;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserType;
import com.revature.Revinsure.repo.ClaimDao;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class ClaimServiceTest extends RevinsureApplicationTests {
	
	@MockBean
	private static ClaimDao claimDao;
	
	@Autowired
	@InjectMocks
	private ClaimService claimService = new ClaimServiceImpl();
	
	private final User fakeUser = new User(83, "fake@email.com", "password", UserType.PATIENT, null, null, null, null, null);
	private final Claim fakeClaim = new Claim(-1, null, null, new Date(), 100, Status.PENDING, "test description");
	private final Claim updatedFakeClaim = new Claim(10, null, null, new Date(), 100, Status.PENDING, "test description");
	private final Claim nullDate = new Claim(-1, null, null, null, 100, Status.PENDING, "test description");
	private final Claim nullStatus = new Claim(-1, null, null, new Date(), 100, null, "test description");
	private final Claim nullDescription = new Claim(-1, null, null, new Date(), 100, Status.PENDING, null);
	
	
	
	@BeforeClass
	public static void fakeMyDao() {
		MockitoAnnotations.openMocks(claimDao);

	}
	
	@Test
	public void testAddClaim() {
		when(claimDao.save(fakeClaim)).thenReturn(updatedFakeClaim);
		when(claimDao.save(nullDate)).thenReturn(nullDate);
		when(claimDao.save(nullStatus)).thenReturn(nullStatus);
		when(claimDao.save(nullDescription)).thenReturn(nullDescription);
		
		assertTrue(claimService.addClaim(fakeUser, fakeClaim));
		assertFalse(claimService.addClaim(fakeUser, null));
		assertFalse(claimService.addClaim(fakeUser, nullDate));
		assertFalse(claimService.addClaim(fakeUser, nullStatus));
		assertFalse(claimService.addClaim(fakeUser, nullDescription));
	}
	
	@Test
	public void testGetUserClaims() {
//		User user
		
	}
	
	@Test
	public void testUpdateClaimStatus() {
//		Claim claim, Status newStatus
	}
	
	@Test
	public void testGetAllClaims() {
		
	}

}
