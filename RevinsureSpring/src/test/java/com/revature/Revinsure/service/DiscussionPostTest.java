package com.revature.Revinsure.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;


import com.revature.Revinsure.RevinsureApplicationTests;
import com.revature.Revinsure.models.DiscussionPost;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserType;
import com.revature.Revinsure.repo.DiscussionPostDao;
import com.revature.Revinsure.services.ForumService;
import com.revature.Revinsure.services.ForumServiceImpl;



@RunWith(MockitoJUnitRunner.class)
public class DiscussionPostTest extends RevinsureApplicationTests {
	
	@MockBean
	private static DiscussionPostDao discussionPostDao;
	
	
	
	private static DiscussionPost fakePost = new DiscussionPost(-1, "post title", "post content", LocalDate.now(), new User());
	private static DiscussionPost updatedFakePost = new DiscussionPost(1, "post title", "post content", LocalDate.now(), new User());
	private static DiscussionPost fakePost1 = new DiscussionPost(-1, "post title", "", LocalDate.now(), new User());
	private static DiscussionPost fakePost2 = new DiscussionPost(-1, "", "post content", LocalDate.now(), new User());
	
	private static DiscussionPost firstPost = new DiscussionPost(1, "post title", "post content", LocalDate.now(), new User());     
	private static DiscussionPost secondPost = new DiscussionPost(2, "post title", "post content", LocalDate.now(), new User());
	
	@Autowired
	@InjectMocks
	public final ForumService forumService = new ForumServiceImpl();
	
	@BeforeClass
	void setup() {
		MockitoAnnotations.openMocks(discussionPostDao);
	}
	
	@Test
	public void testGetAllPosts() {
		
		List<DiscussionPost> dis = new ArrayList<>();
		dis.add(firstPost);
		dis.add(secondPost);
		when(discussionPostDao.findAll()).thenReturn(dis);
		assertNotEquals(forumService.getAllPosts(), dis);
		assertNotNull(forumService.getAllPosts());
			
	}
	
	@Test
	public void testCreateNewPost() {
		User fakeUser = new User(1,"fake@gmail.com","fake", UserType.PATIENT); 
		when(discussionPostDao.save(fakePost)).thenReturn(updatedFakePost);
		when(discussionPostDao.save(fakePost1)).thenReturn(null);
		when(discussionPostDao.save(fakePost2)).thenReturn(fakePost2);
		
		
		
		assertTrue(forumService.createNewPost(fakeUser, fakePost));
	//	assertFalse(forumService.createNewPost(fakeUser, null));
		assertFalse(forumService.createNewPost(fakeUser, fakePost1));
		assertTrue(forumService.createNewPost(fakeUser, fakePost2));
		
	}
}

