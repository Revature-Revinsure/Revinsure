package com.revature.Revinsure.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.Revinsure.models.DiscussionPost;

public interface DiscussionPostDao extends JpaRepository <DiscussionPost, Integer> {

	
}
