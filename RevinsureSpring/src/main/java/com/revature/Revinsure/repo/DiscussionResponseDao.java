package com.revature.Revinsure.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.Revinsure.models.DiscussionResponse;


@Repository("discussionResponseDao")
@Transactional
public interface DiscussionResponseDao extends JpaRepository <DiscussionResponse, Integer> {

	
}
