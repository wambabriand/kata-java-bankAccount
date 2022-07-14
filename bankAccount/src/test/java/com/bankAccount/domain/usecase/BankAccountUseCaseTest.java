package com.bankAccount.domain.usecase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bankAccount.domain.exceptions.AccountException;

public class BankAccountUseCaseTest {
	
	
	private BankAccountUse underTest;
	
	@BeforeEach
	private void initBankAccount() {
		this.underTest= new BankAccountUse();
	}
	
	
	// I need to create account to be able to perform operation with it
	@Test
	public void createAccountWithValidParams() throws AccountException{
		String validInfo = "valid infor";
		AccountApi accountApi = underTest.createAccount(validInfo);
		System.out.println(accountApi);
		assertThat(accountApi.getId()).isPositive();
		assertThat(accountApi.getInfoAccount()).isEqualTo(validInfo);
		assertThat(accountApi.getBalance()).isEqualTo(0);
	}
	
	@Test
	public void createAccountWithInvalidParams() {
		
	}
		
	// test the deposit operation
	@Test
	public void depositWithValidParams() {
			
	}
	
	
	// test the deposit operation
	@Test
	public void depositWithInvalidParams() {
			
	}
	
	
	// test the withdrawal operation
	@Test
	public void withdrawalWithValidParams() {
		
	}

	// test the withdrawal operation
	@Test
	public void withdrawalWithInvalidParams() {
		
	}
	
	// test the list operation
	@Test
	public void getOperationsWithValidParams() {
		
	}
	
	// test the list operation
	@Test
	public void getOperationsWithInvalidParams() {
		
	}

}
