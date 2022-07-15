package com.bankAccount.entrypoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankAccount.adapters.AccountAdapterImpl;
import com.bankAccount.adapters.AccountRepository;
import com.bankAccount.adapters.OperationRepository;
import com.bankAccount.domain.usecase.AccountApi;
import com.bankAccount.domain.usecase.BankAccountUseCase;
import com.bankAccount.domain.usecase.OperationApi;

@RestController
public class BankController {

/*
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private OperationRepository operationRepository;
	*/
	
	private BankAccountUseCase bankAccountUseCase;
	
	public BankController(OperationRepository operationRepository, AccountRepository accountRepository ) {
		bankAccountUseCase = new BankAccountUseCase(new AccountAdapterImpl(accountRepository, operationRepository));
	}
	
	@GetMapping("/api/accounts/{idAccount}/operations")
	public List<OperationApi> getOperations(@PathVariable Long idAccount) {
		try {
			return bankAccountUseCase.getOperations(idAccount);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new ArrayList<OperationApi>();
		}
		
	}
	

	@PostMapping("/api/accounts")
	public AccountApi createAccount(@RequestBody AccountApi accountApi) {
		try {
			return bankAccountUseCase.createAccount(accountApi.getInfoAccount());
		}catch (Exception e) {
			return null;
		}
		
	}
	
	@PostMapping("/api/accounts/{idAccount}/operations")
	public OperationApi doDeposit(@RequestBody OperationApi operationApi, @PathVariable Long idAccount) {
		try {
			if(operationApi.getType().equals("DEPOSIT")) {
				return bankAccountUseCase.doDeposit(idAccount, operationApi.getAmount());
			} else if(operationApi.getType().equals("WITHDRAWAL")) {
				return bankAccountUseCase.doWithdrawal(idAccount, operationApi.getAmount());
			}
			return null;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	
}
