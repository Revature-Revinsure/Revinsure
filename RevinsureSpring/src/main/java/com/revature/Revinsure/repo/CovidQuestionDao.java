package com.revature.Revinsure.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.revature.Revinsure.models.CovidQuestion;
import com.revature.Revinsure.models.User;

public interface CovidQuestionDao extends JpaRepository <CovidQuestion, Integer> { //user or integer for key?

	@Modifying
	@Query("update CovidQuestion covidQuestion set covidQuestion.hasCovid = ?2 where covidQuestion.user.userId = ?1")
	int updateCovidQuestion(CovidQuestion covidQuestion);
}
