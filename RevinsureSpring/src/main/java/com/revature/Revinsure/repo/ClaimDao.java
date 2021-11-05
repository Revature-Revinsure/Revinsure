package com.revature.Revinsure.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.Revinsure.models.Claim;

@Repository("claimDao")
@Transactional
public interface ClaimDao extends JpaRepository <Claim, Integer> {

}
