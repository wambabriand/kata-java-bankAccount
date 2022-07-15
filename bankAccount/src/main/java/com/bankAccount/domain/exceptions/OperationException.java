package com.bankAccount.domain.exceptions;

public class OperationException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public OperationException(String message) {
		super(message);
	}
}
