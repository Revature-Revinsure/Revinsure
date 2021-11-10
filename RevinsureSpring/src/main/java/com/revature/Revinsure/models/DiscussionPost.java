package com.revature.Revinsure.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "discussion_posts")
public class DiscussionPost {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "post_id")
	private int id;
	
	@Column(name = "post_title")
	private String title;//the users question, more info can provided in a response
	
	@Column(name = "post_content")
	private String content;
	
	@Column(name = "date_submitted")
	@DateTimeFormat(pattern = "MM.dd.yyyy")
	private LocalDate  dateOfBirth;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
//	@OneToMany(mappedBy = "post")
//	private List<DiscussionResponse> responses;

	@Override
	public String toString() {
		return "DiscussionPost [id=" + id + ", title=" + title + ", user=" + user.getId() +  "]";
	}
	
	
}
