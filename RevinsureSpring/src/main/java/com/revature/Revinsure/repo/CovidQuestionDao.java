package com.revature.Revinsure.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.Revinsure.models.CovidQuestion;

public interface CovidQuestionDao extends JpaRepository <CovidQuestion, Integer> { //user or integer for key?
	
}
