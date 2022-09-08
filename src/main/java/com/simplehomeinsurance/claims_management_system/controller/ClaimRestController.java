package com.simplehomeinsurance.claims_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplehomeinsurance.claims_management_system.entity.Claim;
import com.simplehomeinsurance.claims_management_system.entity.Policy;
import com.simplehomeinsurance.claims_management_system.entity.PolicyHolder;
import com.simplehomeinsurance.claims_management_system.service.ClaimService;
import com.simplehomeinsurance.claims_management_system.service.PolicyHolderService;
import com.simplehomeinsurance.claims_management_system.service.PolicyService;

@RestController
@RequestMapping("/api")
public class ClaimRestController {	
	@Autowired
	private ClaimService claimService;	
	@Autowired
	private PolicyHolderService policyHolderService;	
	@Autowired
	private PolicyService policyService;
	
	@GetMapping("/claims")
	public List<Claim> getClaims() {	
		return claimService.getClaimsList();
	}
	
	@GetMapping("claims/{claimNumber}")
	public Claim showClaimDetails(@PathVariable String claimNumber) {
		return claimService.getClaim(claimNumber);
	}
	
	@PostMapping("policyholders/{policyholderNumber}/policies/{policyNumber}/claims")
	public Claim addClaim(@PathVariable String policyholderNumber,
						  @PathVariable String policyNumber,
						  @RequestBody Claim claim) {
		PolicyHolder policyHolder = policyHolderService.getPolicyHolder(policyholderNumber);	
		Policy policy = policyService.getPolicy(policyNumber);		
		policyHolder.addClaim(claim);
		claim.setPolicyHolder(policyHolder);
		claim.setPolicy(policy);
		claimService.saveClaim(claim);
		return claim;
	}

}
