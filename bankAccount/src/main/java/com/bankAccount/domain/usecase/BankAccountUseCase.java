package com.bankAccount.domain.usecase;

import java.util.ArrayList;
import java.util.List;

import com.bankAccount.adapters.AccountAdapterImpl;
import com.bankAccount.domain.entities.Account;
import com.bankAccount.domain.entities.Operation;
import com.bankAccount.domain.exceptions.AccountException;
import com.bankAccount.domain.exceptions.OperationException;


public class BankAccountUseCase {

	private AccountAdapter accountAdapter;
	
	public BankAccountUseCase(AccountAdapterImpl accountAdapterImpl) {
		this.accountAdapter= accountAdapterImpl;
	}

	public AccountApi createAccount(String accountInfo ) throws AccountException {
		Account account = new Account(accountInfo);
		account = accountAdapter.saveAccount(account);
		return AccountMapperApi.mapAccountToAccountApi(account);
	}
	
	public AccountApi getAccount(Long idAccount ) throws AccountException{
		return AccountMapperApi.mapAccountToAccountApi(findAccount(idAccount));
	}
	

	private Account findAccount(Long idAccount ) throws AccountException{
		Account account = accountAdapter.findAccountById(idAccount);
		if(account == null) {
			throw new AccountException("ACCOUNT DOESN'T EXISTS WITH THIS ID "+idAccount);
		}
		return account;
	}

	public OperationApi doDeposit(Long idAccount, int amount ) throws AccountException, OperationException {
		Account account = findAccount(idAccount);
		Operation operation = account.createDeposit(amount);
		account.setBalance(account.getBalance() + amount);
		operation = accountAdapter.updateAccountAndSaveOperation(account, operation);
		OperationApi operationApi = AccountMapperApi.mapOperationToOperationApi(operation);
		operationApi.setBalance(account.getBalance());
		return operationApi;
	}
	

	public OperationApi doWithdrawal(Long idAccount, int amount ) throws AccountException, OperationException {
		Account account = findAccount(idAccount);
		Operation operation = account.createWithdrawal(amount);
		account.setBalance(account.getBalance() - amount);
		operation = accountAdapter.updateAccountAndSaveOperation(account, operation);
		OperationApi operationApi = AccountMapperApi.mapOperationToOperationApi(operation);
		operationApi.setBalance(account.getBalance());
		return operationApi;
	}
	

	public List<OperationApi> getOperations(Long idAccount ) throws AccountException{
		int balance = 0;
		OperationApi currentOperation;
		List<OperationApi> operationApis = new ArrayList<OperationApi>();
		
		findAccount(idAccount);
		
		List<Operation> operations = accountAdapter.findOperationsByAccountId(idAccount);
		
		for(Operation operation:operations) {
			currentOperation = AccountMapperApi.mapOperationToOperationApi(operation);
			if(operation.getType().equals("DEPOSIT")) {
				balance = balance + operation.getAmount();
			}else {
				balance = balance - operation.getAmount();
			}
			currentOperation.setBalance(balance);
			operationApis.add(currentOperation);
		}
		return operationApis;
	}
	
}
