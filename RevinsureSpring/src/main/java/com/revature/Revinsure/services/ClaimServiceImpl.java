package com.revature.Revinsure.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.Revinsure.models.Claim;
import com.revature.Revinsure.models.Status;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.repo.ClaimDao;
import com.revature.Revinsure.repo.UserDao;

@Service("claimService")
public class ClaimServiceImpl implements ClaimService {

	private static final Logger log = Logger.getLogger(ClaimServiceImpl.class);
	
	@Autowired
	private ClaimDao claimDao;

	public ClaimServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addClaim(User user, Claim claim) {
		boolean success = false;

		if (claim != null && claim.getDateOfClaim() != null && claim.getDescription() != null
				&& claim.getStatus() != null) {
			claim.setUser(user);

			try {
				claimDao.save(claim);
				log.info("addClaim called for claim: " + claim.toString());
				success = true;
			} catch (Exception e) {
				log.warn("An exception has been caught!");
				e.printStackTrace();

			}
		}

		return success;
	}

	@Override
	public List<Claim> getUserClaims(User user) {
		log.info("getUserClaims called for user id: " + user.getId());
		return claimDao.getClaimByUser(user);
	}

	@Override
	public boolean updateClaimStatus(Claim claim, Status newStatus) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Claim> getAllClaims() {
		// TODO Auto-generated method stub
		return null;
	}

}
