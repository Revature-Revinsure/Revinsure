package com.revature.Revinsure.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.Revinsure.models.DiscussionPost;

@Repository("discussionPostDao")
public interface DiscussionPostDao extends JpaRepository <DiscussionPost, Integer> {

}
