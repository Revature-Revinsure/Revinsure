package com.revature.Revinsure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.Revinsure.models.Claim;
import com.revature.Revinsure.models.Status;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.repo.ClaimDao;
import com.revature.Revinsure.repo.UserDao;

@Service("claimService")
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	private ClaimDao claimDao;

	public ClaimServiceImpl() {
	}

	@Override
	public boolean addClaim(User user, Claim claim) {
		boolean success = false;

		if (claim != null && claim.getDateOfClaim() != null && claim.getDescription() != null
				&& claim.getStatus() != null) {
			claim.setUser(user);
			try {
				claimDao.save(claim);
				success = true;
			} catch (Exception e) {
				e.printStackTrace();

			}
		}

		return success;
	}

	@Override
	public List<Claim> getUserClaims(User user) {
		return claimDao.getClaimByUser(user);
	}

	@Override
	public boolean updateClaimStatus(Claim claim, Status newStatus) {
		return false;
	}

	@Override
	public List<Claim> getAllClaims() {
		return null;
	}

}
