package com.revature.Revinsure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.Revinsure.models.DiscussionPost;
import com.revature.Revinsure.models.DiscussionResponse;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.repo.DiscussionPostDao;
import com.revature.Revinsure.repo.DiscussionResponseDao;

@Service("forumService")
public class ForumServiceImpl implements ForumService {

	@Autowired
	private DiscussionPostDao discussionPostDao;
	
	@Autowired
	private DiscussionResponseDao discussionResponseDao;
	
	public ForumServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean createNewPost(User user, DiscussionPost post) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removePost(DiscussionPost post) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeResponse(DiscussionResponse response) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createNewResponse(User user, DiscussionResponse response) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<DiscussionResponse> getResponsesForPost(DiscussionPost post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DiscussionPost> getAllPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DiscussionPost> getPostsByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
