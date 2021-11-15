package com.revature.Revinsure.services;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.Revinsure.models.DiscussionPost;
import com.revature.Revinsure.models.DiscussionResponse;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.repo.DiscussionPostDao;
import com.revature.Revinsure.repo.DiscussionResponseDao;

@Service("forumService")
public class ForumServiceImpl implements ForumService {

	private static final Logger log = Logger.getLogger(ForumServiceImpl.class);
	
	@Autowired
	private DiscussionPostDao discussionPostDao;

	@Autowired
	private DiscussionResponseDao discussionResponseDao;

	public ForumServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean createNewPost(User user, DiscussionPost post) {
		post.setUser(user);
		post.setDateSubmitted(LocalDate.now());

		boolean success = false;
		if (discussionPostDao.save(post) != null) {
			log.info("createNewPost called for post: " + post.toString());
			success = true;
		}

		return success;
	}

//	@Override
//	public boolean removePost(DiscussionPost post) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean removeResponse(DiscussionResponse response) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	@Override
	public boolean createNewResponse(User user, DiscussionResponse response) {
		response.setUser(user);
		response.setDateSubmitted(LocalDate.now());

		boolean success = false;
		if (discussionResponseDao.save(response) != null) {
			log.info("createNewResponse called for response: " + response.toString());
			success = true;
		}

		return success;
	}

	@Override
	public List<DiscussionResponse> getResponsesForPost(DiscussionPost post) {
		log.info("getResponsesForPost called for post: " + post.toString());
		return discussionResponseDao.findByPostId(post.getId());
	}

	@Override
	public List<DiscussionPost> getAllPosts() {
		log.info("getAllPosts called.");
		return discussionPostDao.findAllByOrderByIdDesc();
	}

//	@Override
//	public List<DiscussionPost> getPostsByUser(User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
