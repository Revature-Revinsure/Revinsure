package com.revature.Revinsure.repo;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.Revinsure.models.DiscussionPost;

@Repository("discussionPostDao")
@Transactional
public interface DiscussionPostDao extends JpaRepository<DiscussionPost, Integer> {

	public List<DiscussionPost> findAllByOrderByIdDesc();

}
