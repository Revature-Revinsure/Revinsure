package com.revature.Revinsure.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "covid_forms")
public class CovidQuestion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "question_id")
	private int id;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "has_covid")
	private boolean hasCovid;
	
	@Column(name = "around_covid")
	private boolean aroundCovid;
	
	@Column(name = "date_answered")
	private Date dateAnswered;

	@Override
	public String toString() {
		return "CovidQuestion [user=" + user.getId() + ", hasCovid=" + hasCovid + ", aroundCovid=" + aroundCovid
				+ ", dateAnswered=" + dateAnswered + "]";
	}

	
}
