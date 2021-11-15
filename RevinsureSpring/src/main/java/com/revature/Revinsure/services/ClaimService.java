package com.revature.Revinsure.services;

import java.util.List;

import com.revature.Revinsure.models.Claim;
import com.revature.Revinsure.models.Status;
import com.revature.Revinsure.models.User;

public interface ClaimService {

	boolean addClaim(User user, Claim claim);

	List<Claim> getUserClaims(User user);

	boolean updateClaimStatus(Claim claim, Status newStatus);

	List<Claim> getAllClaims();

	// remember @Service annotation when building corresponding named class
}
