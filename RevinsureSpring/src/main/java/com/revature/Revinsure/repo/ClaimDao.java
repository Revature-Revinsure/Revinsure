package com.revature.Revinsure.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.Revinsure.models.Claim;

public interface ClaimDao extends JpaRepository <Claim, Integer> {

}
