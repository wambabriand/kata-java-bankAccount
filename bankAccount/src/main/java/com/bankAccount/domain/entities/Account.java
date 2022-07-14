package com.bankAccount.domain.entities;

import java.util.List;

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
	
}
