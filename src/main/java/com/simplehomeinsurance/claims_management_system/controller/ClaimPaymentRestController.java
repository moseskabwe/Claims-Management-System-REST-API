package com.simplehomeinsurance.claims_management_system.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplehomeinsurance.claims_management_system.entity.Claim;
import com.simplehomeinsurance.claims_management_system.entity.ClaimPayment;
import com.simplehomeinsurance.claims_management_system.service.ClaimPaymentService;
import com.simplehomeinsurance.claims_management_system.service.ClaimService;
import com.simplehomeinsurance.claims_management_system.utils.DateUtils;

@RestController
@RequestMapping("/api")
public class ClaimPaymentRestController {		
	private ClaimPaymentService claimPaymentService;
	@Autowired
	private ClaimService claimService;
	@Autowired
	public ClaimPaymentRestController(ClaimPaymentService theClaimPaymentService) {
		claimPaymentService = theClaimPaymentService;		
	}
	
	@GetMapping("/payments")
	public List<ClaimPayment> getPayments() {				
		return claimPaymentService.getClaimPaymentList();
	}
	
	@GetMapping("/payments/{paymentNumber}")
	public ClaimPayment getPayment(@PathVariable int paymentNumber) {				
		return claimPaymentService.getClaimPayment(paymentNumber);
	}
	
	@GetMapping("/claims/{claimNumber}/payments")
	public List<ClaimPayment> getClaimPaymentForClaim(@PathVariable String claimNumber) {
		Claim claim = claimService.getClaim(claimNumber);
		return claim.getPayments();		
	}
	
	@PreAuthorize("hasRole('ADJUSTER')")
	@PostMapping("/claims/{claimNumber}/payments")
	public ClaimPayment addPayment(@PathVariable String claimNumber,
									@RequestBody ClaimPayment payment) {
		Claim claim = claimService.getClaim(claimNumber);
		claim.addPayment(payment);
		claim.setStatus("Finalised");
		Date date = new Date();
		payment.setClaim(claim);
		payment.setPaymentDate(DateUtils.formatDate(date));
		claimPaymentService.saveClaimPayment(payment);
		claimService.updateClaim(claim);
		return payment;
	}
}
