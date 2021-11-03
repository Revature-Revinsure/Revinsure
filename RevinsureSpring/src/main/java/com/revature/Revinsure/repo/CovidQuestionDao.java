package com.revature.Revinsure.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.Revinsure.models.CovidQuestion;
import com.revature.Revinsure.models.User;

public interface CovidQuestionDao extends JpaRepository <CovidQuestion, User> { //user or integer for key?

}
