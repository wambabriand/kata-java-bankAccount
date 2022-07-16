package com.calculato;

public class NegativesNotAllowed extends Exception {

	private static final long serialVersionUID = 1L;

	public NegativesNotAllowed(String message) {
		super(message);
	}
	
}
