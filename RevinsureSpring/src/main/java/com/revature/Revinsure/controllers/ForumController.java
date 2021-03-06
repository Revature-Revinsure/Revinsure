package com.revature.Revinsure.controllers;

import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Revinsure.models.DiscussionPost;
import com.revature.Revinsure.models.DiscussionResponse;
import com.revature.Revinsure.models.Message;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserType;
import com.revature.Revinsure.services.ForumService;

@RestController("forumController")
@RequestMapping("/discussion")
@CrossOrigin(origins = { "http://localhost:4200", "http://revinsure.s3-website.us-east-1.amazonaws.com/" }, allowCredentials = "true")
public class ForumController {

	private static final Logger log = Logger.getLogger(ForumController.class);
	
	@Autowired
	private ForumService forumService;

	public ForumController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping(value = "/post")
	public List<DiscussionPost> getAllPosts(HttpSession session) {
		log.debug("getAllPosts has been called.");
		if (session.getAttribute("loggedInUser") == null) {
			List<DiscussionPost> discussion = new ArrayList<>();
			DiscussionPost dp = new DiscussionPost(-1, "Sorry but you must login to see the forum.", "",
					LocalDate.now(), new User());

			discussion.add(dp);
			log.warn("an unauthenticated user tried to getAllPosts!");
			return discussion;
		}

		return forumService.getAllPosts();
	}
	
	@GetMapping(value = "/myPosts")
	public List<DiscussionPost> getMyPosts(HttpSession session) {

		if (session.getAttribute("loggedInUser") == null) {
			List<DiscussionPost> discussion = new ArrayList<>();
			DiscussionPost dp = new DiscussionPost(-1, "Sorry but you must login to see the forum.", "",
					LocalDate.now(), new User());

			discussion.add(dp);

			return discussion;
		}

		User u = (User) session.getAttribute("loggedInUser");
		return forumService.getPostsByUser(u);
	}

	@PostMapping(value = "/get/response")
	public List<DiscussionResponse> getAllResponsesByPost(HttpSession session,
			@RequestBody DiscussionPost currentPost) {
		log.debug("getAllResponsesByPost has been called.");
		if (session.getAttribute("loggedInUser") == null) {
			List<DiscussionResponse> discussion = new ArrayList<>();
			DiscussionResponse dr = new DiscussionResponse(-1, "Sorry but you must login to see the forum.",
					LocalDate.now(), new User(), new DiscussionPost());

			discussion.add(dr);
			log.warn("an unauthenticated user tried to getAllResponsesByPost!");
			return discussion;
		}

		session.setAttribute("post", currentPost);
		currentPost.setUser((User) session.getAttribute("loggedInUser"));

		return forumService.getResponsesForPost(currentPost);
	}

	@PostMapping(value = "/post")
	public boolean makePost(HttpSession session, @RequestBody DiscussionPost currentPost) {
		log.debug("makePost has been called.");
		if (session.getAttribute("loggedInUser") == null) {

			return false;
		}

		User user = (User) session.getAttribute("loggedInUser");

		return forumService.createNewPost(user, currentPost);
	}

	@PostMapping(value = "/response")
	public boolean makeResponse(HttpSession session, @RequestBody DiscussionResponse currentResponse) {
		log.debug("makeResponse has been called.");
		if (session.getAttribute("loggedInUser") == null) {

			return false;
		}

		User user = (User) session.getAttribute("loggedInUser");

		currentResponse.setPost((DiscussionPost) session.getAttribute("post"));
		return forumService.createNewResponse(user, currentResponse);
	}

}
