package com.simplehomeinsurance.claims_management_system.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplehomeinsurance.claims_management_system.entity.Claim;
import com.simplehomeinsurance.claims_management_system.entity.DeclinedClaim;
import com.simplehomeinsurance.claims_management_system.service.ClaimService;
import com.simplehomeinsurance.claims_management_system.service.DeclinedClaimService;
import com.simplehomeinsurance.claims_management_system.utils.DateUtils;

@RestController
@RequestMapping("/api")
public class DeclinedClaimRestController {	
	@Autowired
	private DeclinedClaimService declinedClaimService;	
	@Autowired
	private ClaimService claimService;	
	
	@GetMapping("/declined-claims")
	public List<DeclinedClaim> getDeclinedClaims() {
		return declinedClaimService.getDeclinedClaimList();
	}
	
	@GetMapping("/declined-claims/{declinedClaimNumber}")
	public DeclinedClaim getDeclinedClaim(@PathVariable int declinedClaimNumber) {
		return declinedClaimService.getDeclinedClaim(declinedClaimNumber);
	}
	
	@GetMapping("/claims/{claimNumber}/declined-claims")
	public DeclinedClaim getDeclinedClaimForClaim(@PathVariable String claimNumber) {
		Claim claim = claimService.getClaim(claimNumber);
		return claim.getDeclinedClaim();
	}
	
	@PostMapping("claims/{claimNumber}/declined-claims")
	public DeclinedClaim declineClaim(@PathVariable String claimNumber, 
									  @RequestBody DeclinedClaim declinedClaim) {
		Claim claim = claimService.getClaim(claimNumber);
		claim.setDeclinedClaim(declinedClaim);			
		claim.setStatus("Declined");
		Date date = new Date();  			
		declinedClaim.setClaim(claim);			
		declinedClaim.setDeclinedDate(DateUtils.formatDate(date));
		declinedClaimService.saveDeclinedClaim(declinedClaim);
		claimService.updateClaim(claim);
		return declinedClaim;
	}
}
