package com.revature.Revinsure.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Revinsure.models.DiscussionPost;
import com.revature.Revinsure.models.DiscussionResponse;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.services.ForumService;

@RestController("forumController")
@RequestMapping("/discussion")
@CrossOrigin(origins = { "http://localhost:4200" }, allowCredentials = "true")
public class ForumController {

	@Autowired
	private ForumService forumService;

	public ForumController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping(value = "/post")
	public List<DiscussionPost> getAllPosts() {
		return forumService.getAllPosts();
	}

	@PostMapping(value = "/get/response")
	public List<DiscussionResponse> getAllResponsesByPost(HttpSession session,@RequestBody DiscussionPost currentPost) {
		session.setAttribute("post", currentPost);
		currentPost.setUser((User)session.getAttribute("loggedInUser"));

		return forumService.getResponsesForPost(currentPost);
	}

	@PostMapping(value = "/post")
	public boolean makePost(HttpSession session, @RequestBody DiscussionPost currentPost) {
		User user = (User) session.getAttribute("loggedInUser");

		return forumService.createNewPost(user, currentPost);
	}

	@PostMapping(value = "/response")
	public boolean makeResponse(HttpSession session, @RequestBody DiscussionResponse currentResponse) {
		User user = (User) session.getAttribute("loggedInUser");

		currentResponse.setPost((DiscussionPost)session.getAttribute("post"));
		return forumService.createNewResponse(user, currentResponse);
	}
	
}
