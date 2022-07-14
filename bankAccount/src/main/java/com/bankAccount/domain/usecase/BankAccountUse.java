package com.bankAccount.domain.usecase;

import java.util.List;

import com.bankAccount.domain.entities.Account;
import com.bankAccount.domain.exceptions.AccountException;


public class BankAccountUse {

	
	public AccountApi createAccount(String accountInfo ) throws AccountException {
		Account account = new Account(accountInfo);
		return Mapper.mapAccountToAccountApi(account);
	}
	
	public AccountApi getAccount(Long idAccount ) throws AccountException{
		return Mapper.mapAccountToAccountApi(findAccount(idAccount));
	}
	

	private Account findAccount(Long idAccount ) throws AccountException{
		
		return null;
	}

	public OperationApi doDeposit(OperationApi perationApi ) {
		
		return null;
	}
	

	public OperationApi doWithdrawal(OperationApi perationApi ) {
		
		return null;
	}
	

	public List<OperationApi> getOperations(OperationApi perationApi ) {
		
		return null;
	}
	
}
