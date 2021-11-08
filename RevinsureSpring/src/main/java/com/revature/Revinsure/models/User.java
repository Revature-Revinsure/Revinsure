package com.revature.Revinsure.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User {
	
	public User(String string, String string2) {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "user_type")
	private UserType type;
//	
//	@OneToOne(mappedBy = "user")
//	private UserInfo info;
//	
//	@OneToOne(mappedBy = "user")
//	private CovidQuestion question;
	
	@OneToMany(mappedBy = "user")
	private List<Claim> claims;

	@OneToMany(mappedBy = "user")
	private List<DiscussionPost> posts;
	
	@OneToMany(mappedBy = "user")
	private List<DiscussionResponse> responses;
}
