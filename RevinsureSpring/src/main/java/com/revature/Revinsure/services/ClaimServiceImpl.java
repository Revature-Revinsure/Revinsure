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
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addClaim(User user, Claim claim) {
		boolean success = false;
		claim.setUser(user);
		
		try {
			claimDao.save(claim);
			success = true;
		}
		catch(Exception e) {
//			e.printStackTrace();
			
		}
		
		
		return success;
	}

	@Override
	public List<Claim> getUserClaims(User user) {
		// TODO Auto-generated method stub
		return null;
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
