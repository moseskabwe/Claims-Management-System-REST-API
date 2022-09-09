package com.simplehomeinsurance.claims_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplehomeinsurance.claims_management_system.entity.Policy;
import com.simplehomeinsurance.claims_management_system.entity.PolicyHolder;
import com.simplehomeinsurance.claims_management_system.service.PolicyHolderService;
import com.simplehomeinsurance.claims_management_system.service.PolicyService;

@RestController
@RequestMapping("/api")
public class PolicyRestController {
	@Autowired
	private PolicyService policyService;	
	@Autowired
	private PolicyHolderService policyHolderService;	
	
	@RequestMapping("/policies")
	public List<Policy> getPolicies() {	
		return policyService.getPolicies();	
	}
	
	@GetMapping("/policies/{policyNumber}")
	public Policy getPolicyholder(@PathVariable("policyNumber") 
										  String policyNumber) {
		return policyService.getPolicy(policyNumber);
	}
	
	@GetMapping("/policyholders/{policyholderNumber}/policies")
	public List<Policy> getPolicyholderPolicies(@PathVariable String policyholderNumber) {
		PolicyHolder policyholder = policyHolderService.getPolicyHolder(policyholderNumber);
		return policyholder.getPolicies();
	}
}
