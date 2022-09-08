package com.simplehomeinsurance.claims_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplehomeinsurance.claims_management_system.entity.ClaimPayment;
import com.simplehomeinsurance.claims_management_system.service.ClaimPaymentService;

@RestController
@RequestMapping("/api")
public class ClaimRestPaymentController {		
	private ClaimPaymentService claimPaymentService;	
	@Autowired
	public ClaimRestPaymentController(ClaimPaymentService theClaimPaymentService) {
		claimPaymentService = theClaimPaymentService;
	}
	
	@GetMapping("/payments")
	public List<ClaimPayment> showPayments() {				
		return claimPaymentService.getClaimPaymentList();
	}
	
	@GetMapping("/payments/{paymentNumber}")
	public ClaimPayment getPayment(@PathVariable int paymentNumber) {				
		return claimPaymentService.getClaimPayment(paymentNumber);
	}

//	@GetMapping("/finaliseClaim")
//	public String finaliseClaim(@ModelAttribute("claimNumber") String claimNumber, 
//								Model model) {	
//		Claim claim = claimService.getClaim(claimNumber);	
//		ClaimPayment payment = new ClaimPayment();	
//		model.addAttribute("payment", payment)
//			 .addAttribute("claim", claim);	
//		return "finalise-claim";
//	}

//	@PostMapping("/finaliseClaim")
//	public String makePayment(@ModelAttribute("claimNumber") String claimNumber, 
//							  @ModelAttribute("payment") ClaimPayment payment,
//							  BindingResult bindingResult, Model model) {
//		Claim claim = claimService.getClaim(claimNumber);
//		if (bindingResult.hasErrors()) {
//			model.addAttribute("payment", payment)
//				 .addAttribute("claim", claim);
//			return "finalise-claim";
//		} else {
//			claim.addPayment(payment);
//			claim.setStatus("Finalised");
//			claimService.updateClaim(claim);
//			Date date = new Date();
//			payment.setClaim(claim);
//			payment.setPaymentDate(DateUtils.formatDate(date));
//			claimPaymentService.saveClaimPayment(payment);
//			return "redirect:/dashboard/listClaims/showClaimDetails?claimNumber=" + claimNumber;
//		}
//	}
}
