package com.bankAccount.entrypoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

	
	@GetMapping("/api/accounts/{id}/operations")
	public String getOperations(@PathVariable Long id) {
		
		return "Hello";
	}
	
	
}
