package com.revature.Revinsure.services;

import java.util.List;

import com.revature.Revinsure.models.DiscussionPost;
import com.revature.Revinsure.models.DiscussionResponse;
import com.revature.Revinsure.models.User;

public interface ForumService {

	boolean createNewPost(User user, DiscussionPost post);
	
	boolean removePost(DiscussionPost post); //for employees only
	
	boolean removeResponse(DiscussionResponse response); //patients may remove their responses, employees may remove any response
	
	boolean createNewResponse(User user, DiscussionPost post, DiscussionResponse response);
	
	List<DiscussionResponse> getResponsesForPost(DiscussionPost post);
	
	List<DiscussionPost> getAllPosts(); //any user can see the whole forum
	
	List<DiscussionPost> getPostsByUser(User user);
	
	//stretch goal, push notification to users whose posts receive response
	//stretch goal, push notification when user's claim status changes
	//stretch goal, up-/down-voting responses, timestamp to filter recent, etc
}
