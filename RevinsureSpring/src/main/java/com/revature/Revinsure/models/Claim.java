package com.revature.Revinsure.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "claims")
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "claim_id")
	private int id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "date_of_service")
	private Date dateOfService;

	@Column(name = "date_of_claim")
	private Date dateOfClaim;

	@Column(name = "amount")
	private double amount;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	@Column(name = "description")
	private String description;

	@Override
	public String toString() {
		return "Claim [id=" + id + ", user=" + user.getId() + ", dateOfService=" + dateOfService + ", dateOfClaim="
				+ dateOfClaim + ", amount=" + amount + ", status=" + status + ", description=" + description + "]";
	}

}
