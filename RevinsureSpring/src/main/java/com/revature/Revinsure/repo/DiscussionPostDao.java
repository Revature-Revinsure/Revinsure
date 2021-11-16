package com.revature.Revinsure.repo;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.Revinsure.models.DiscussionPost;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserInfo;

@Repository("discussionPostDao")
@Transactional
public interface DiscussionPostDao extends JpaRepository<DiscussionPost, Integer> {

	public List<DiscussionPost> findAllByOrderByIdDesc();
	public List<DiscussionPost> getPostByUser(User user);

}
