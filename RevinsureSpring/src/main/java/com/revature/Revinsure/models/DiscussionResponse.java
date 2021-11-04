package com.revature.Revinsure.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "discussion_responses")
public class DiscussionResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "response_id")
	private int id;
	
	@Column(name = "content")
	private String content;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "post_id")
	private DiscussionPost post;

	@Override
	public String toString() {
		return "DiscussionResponse [id=" + id + ", content=" + content + ", user=" + user.getId() + ", post=" + post.getId() + "]";
	}
	
	
}
