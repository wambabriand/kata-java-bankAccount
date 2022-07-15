package com.bankAccount.domain.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bankAccount.domain.exceptions.AccountException;
import com.bankAccount.domain.exceptions.OperationException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
	private Long id;
	private String infoAccount;
	private int balance;
	private List<Operation> operations;
	

	public Account(String accountInfo) throws AccountException{
		if(accountInfo == null  || accountInfo.trim().equals("")) {
			throw new AccountException("INVALID ACCOUNT INFORMATION");
		}
		this.infoAccount = accountInfo;
		this.balance = 0;
		this.operations = new ArrayList<Operation>();
	}
	
	
	public Operation createDeposit(int amount)throws OperationException {
		if(amount <= 0) {
			throw new OperationException("INVALID AMOUNT "+amount);
		}
		return new Operation(null,LocalDateTime.now(), "DEPOSIT", amount, this);
	}


	public Operation createWithdrawal(int amount) throws OperationException{
		if(amount <= 0) {
			throw new OperationException("INVALID AMOUNT "+ amount);
		}
		if(amount > this.balance) {
			throw new OperationException("AMOUNT "+ amount +" IS GREATHER THAN YOUR BALANCE "+balance);
		}
		return new Operation(null,LocalDateTime.now(), "WITHDRAWAL", amount, this);
	}
	
}
