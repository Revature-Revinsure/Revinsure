package com.revature.Revinsure.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Revinsure.models.Claim;
import com.revature.Revinsure.models.Message;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.services.ClaimService;

@RestController
@RequestMapping("/api")
public class ClaimController {
	
	@Autowired
	private ClaimService cServ;
	
	//new claim
	@PostMapping("/claim")
	public Message submitClaim(@RequestBody Claim claim, HttpSession session) {
		Message message = new Message();
		
		User user = (User) session.getAttribute("user");//attribute with the user goes here
		
		if(cServ.addClaim(user, claim)) {
			message.setMessage("Claim submitted successfully.");
		}
		else {
			message.setMessage("Claim submission failed.");
		}
		
		return message;
	}

}
