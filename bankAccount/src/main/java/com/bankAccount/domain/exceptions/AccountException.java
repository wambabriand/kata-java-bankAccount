package com.bankAccount.domain.exceptions;

public class AccountException extends Exception{

	private static final long serialVersionUID = 1L;

	public AccountException(String message) {
		super(message);
	}
}
