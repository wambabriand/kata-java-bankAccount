package com.calculato;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

	
	@GetMapping("/api/calculator")
	public String calculator(@RequestParam String equation) {
		try {
			Calculator calculator = new Calculator();
			return ""+ calculator.add(equation);	
		}catch (Exception e) {
			return e.getMessage();
		}
	}
	
}
