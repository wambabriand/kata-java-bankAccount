package com.bankAccount.domain.entities;

import java.util.ArrayList;
import java.util.List;

import com.bankAccount.domain.exceptions.AccountException;

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
	
	
	public Operation doDeposit(int amount) {
		return null;
	}


	public Operation doWithdrawal(int amount) {
		return null;
	}


	public Account(String accountInfo) throws AccountException{
		if(accountInfo == null  || accountInfo.trim().equals("")) {
			throw new AccountException("INVALID ACCOUNT INFORMATION");
		}
		this.infoAccount = accountInfo;
		this.balance = 0;
		this.operations = new ArrayList<Operation>();
	}
	
}
