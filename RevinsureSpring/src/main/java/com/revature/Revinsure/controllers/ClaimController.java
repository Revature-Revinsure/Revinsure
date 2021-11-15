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

import com.revature.Revinsure.models.Claim;
import com.revature.Revinsure.models.Message;
import com.revature.Revinsure.models.User;
import com.revature.Revinsure.models.UserType;
import com.revature.Revinsure.services.ClaimService;

@RestController("claimController")
@CrossOrigin(origins = { "http://localhost:4200" }, allowCredentials = "true")
@RequestMapping("/api")
public class ClaimController {

	@Autowired
	private ClaimService claimService;

	// new claim
	@PostMapping("/claim")
	public Message submitClaim(HttpSession session, @RequestBody Claim claim) {
		Message message = new Message();

		if ((session.getAttribute("accessLevel") == null)
				|| !session.getAttribute("accessLevel").equals(UserType.PATIENT)) {
			message.setMessage("access denied");
			return message;
		}

		User user = (User) session.getAttribute("user");

		if (claimService.addClaim(user, claim)) {
			message.setMessage("Claim submitted successfully.");
		} else {
			message.setMessage("Claim submission failed.");
		}

		return message;
	}

	@GetMapping(value = "/userClaims")
	public List<Claim> getUserClaims(HttpSession session) {
		User u = (User) session.getAttribute("user");
		return claimService.getUserClaims(u);
	}

}
