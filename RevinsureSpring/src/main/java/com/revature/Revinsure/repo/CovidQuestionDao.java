package com.revature.Revinsure.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.Revinsure.models.CovidQuestion;
import com.revature.Revinsure.models.User;

@Repository("covidQuestionDao")
@Transactional
public interface CovidQuestionDao extends JpaRepository<CovidQuestion, Integer> { // user or integer for key?

	CovidQuestion findByUser(User user);
}
