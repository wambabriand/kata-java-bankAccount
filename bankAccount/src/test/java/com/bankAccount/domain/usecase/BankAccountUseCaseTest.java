package com.bankAccount.domain.usecase;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bankAccount.adapters.AccountAdapterImpl;
import com.bankAccount.adapters.AccountRepository;
import com.bankAccount.adapters.OperationRepository;
import com.bankAccount.domain.exceptions.AccountException;
import com.bankAccount.domain.exceptions.OperationException;

@SpringBootTest
public class BankAccountUseCaseTest {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private OperationRepository operationRepository;
	
	
	private BankAccountUseCase underTest;
	
	@BeforeEach
	private void initBankAccount() {
		this.underTest= new BankAccountUseCase(new AccountAdapterImpl(accountRepository, operationRepository));
	}
	
	
	// I need to create account to be able to perform operation with it
	@Test
	public void createAccountWithValidParams() throws AccountException{
		String validInfo = "valid infor";
		AccountApi accountApi = underTest.createAccount(validInfo);
		assertThat(accountApi.getId()).isPositive();
		assertThat(accountApi.getInfoAccount()).isEqualTo(validInfo);
		assertThat(accountApi.getBalance()).isEqualTo(0);
	}
	
	@Test
	public void createAccountWithInvalidParams() {
		String validInfo = null;
		Exception exception = Assertions.assertThrows(AccountException.class, ()-> underTest.createAccount(validInfo));
		assertThat(exception.getMessage()).isEqualTo("INVALID ACCOUNT INFORMATION");
	}
		
	// test the deposit operation
	@Test
	public void depositWithValidParams() throws AccountException, OperationException{
		String validInfo = "valid infor";
		AccountApi accountApi = underTest.createAccount(validInfo);
		OperationApi operationApi1 = underTest.doDeposit(accountApi.getId(), 100);
		assertThat(operationApi1.getType()).isEqualTo("DEPOSIT");
		assertThat(operationApi1.getAmount()).isEqualTo(100);
		assertThat(operationApi1.getBalance()).isEqualTo(100);
		
		OperationApi operationApi2 = underTest.doDeposit(accountApi.getId(), 100);
		assertThat(operationApi2.getType()).isEqualTo("DEPOSIT");
		assertThat(operationApi2.getAmount()).isEqualTo(100);
		assertThat(operationApi2.getBalance()).isEqualTo(200);
	}
	
	
	// test the deposit operation
	@Test
	public void depositWithInvalidParams() throws AccountException, OperationException {
		String validInfo = "valid infor";
		AccountApi accountApi = underTest.createAccount(validInfo);
		Long invalidIdAccount = 0L;
		int invalidAmount = 0;
		Exception exception = null;
		exception = Assertions.assertThrows(AccountException.class, ()-> underTest.doDeposit(invalidIdAccount, 10));
		assertThat(exception.getMessage()).isEqualTo("ACCOUNT DOESN'T EXISTS WITH THIS ID " + invalidIdAccount);
		
		exception = Assertions.assertThrows(OperationException.class, ()-> underTest.doDeposit(accountApi.getId(), invalidAmount));
		assertThat(exception.getMessage()).isEqualTo("INVALID AMOUNT " + invalidAmount);
	}
	
	
	// test the withdrawal operation
	@Test
	public void withdrawalWithValidParams() throws AccountException, OperationException  {
		String validInfo = "valid infor";
		AccountApi accountApi = underTest.createAccount(validInfo);
		underTest.doDeposit(accountApi.getId(), 100);
		
		OperationApi operationApi2 = underTest.doWithdrawal(accountApi.getId(), 10);
		assertThat(operationApi2.getType()).isEqualTo("WITHDRAWAL");
		assertThat(operationApi2.getAmount()).isEqualTo(10);
		assertThat(operationApi2.getBalance()).isEqualTo(90);
		
	}

	// test the withdrawal operation
	@Test
	public void withdrawalWithInvalidParams() throws AccountException, OperationException {
		String validInfo = "valid infor";
		Long invalidIdAccount = 0L;
		int invalidAmount = 10;
		Exception exception = null;
		
		AccountApi accountApi = underTest.createAccount(validInfo);
		
		exception = Assertions.assertThrows(OperationException.class, ()-> underTest.doWithdrawal(accountApi.getId(), invalidAmount));
		assertThat(exception.getMessage()).isEqualTo("AMOUNT "+ invalidAmount +" IS GREATHER THAN YOUR BALANCE "+accountApi.getBalance());
		
		exception = Assertions.assertThrows(AccountException.class, ()-> underTest.doWithdrawal(invalidIdAccount, 10));
		assertThat(exception.getMessage()).isEqualTo("ACCOUNT DOESN'T EXISTS WITH THIS ID " + invalidIdAccount);
		
		underTest.doDeposit(accountApi.getId(), 100);
		
		OperationApi operationApi2 = underTest.doWithdrawal(accountApi.getId(), 10);
		assertThat(operationApi2.getType()).isEqualTo("WITHDRAWAL");
		assertThat(operationApi2.getAmount()).isEqualTo(10);
		assertThat(operationApi2.getBalance()).isEqualTo(90);
	}
	
	// test the list operation
	@Test
	public void getOperationsWithValidParams()  throws AccountException, OperationException {
		
		String validInfo = "valid infor";
		Long invalidIdAccount = 0L;
		int invalidAmount = 1000;
		Exception exception = null;
		
		AccountApi accountApi =underTest.createAccount(validInfo);
		Long idAccount = accountApi.getId();
		int balance = accountApi.getBalance();
		
		underTest.doDeposit(idAccount, 100);
		underTest.doDeposit(idAccount, 100);
		underTest.doWithdrawal(idAccount, 10);
		
		accountApi = underTest.getAccount(idAccount);
		balance = accountApi.getBalance();
		 
		exception = Assertions.assertThrows(OperationException.class, ()-> underTest.doWithdrawal(idAccount, invalidAmount));
		assertThat(exception.getMessage()).isEqualTo("AMOUNT "+ invalidAmount +" IS GREATHER THAN YOUR BALANCE "+balance);
		exception = Assertions.assertThrows(AccountException.class, ()-> underTest.doDeposit(invalidIdAccount, 10));
		assertThat(exception.getMessage()).isEqualTo("ACCOUNT DOESN'T EXISTS WITH THIS ID " + invalidIdAccount);
		
		underTest.doWithdrawal(idAccount, 10);
		underTest.doDeposit(idAccount, 20);
		
		// check the balance 
		OperationApi lastOperationApi = underTest.doWithdrawal(idAccount, 10);
		accountApi = underTest.getAccount(idAccount);

		assertThat(lastOperationApi.getType()).isEqualTo("WITHDRAWAL");
		assertThat(lastOperationApi.getAmount()).isEqualTo(10);
		assertThat(lastOperationApi.getBalance()).isEqualTo(190);
		assertThat(accountApi.getBalance()).isEqualTo(190);
		
		List<OperationApi> operationApis = underTest.getOperations(idAccount);
		lastOperationApi = operationApis.get(5);
		assertThat(lastOperationApi.getType()).isEqualTo("WITHDRAWAL");
		
		// check the number of operation
		assertThat(operationApis.size()).isEqualTo(6);
		
		// check the format 
		assertThat(lastOperationApi.toString()).isEqualTo(
				lastOperationApi.getType() + " "+ lastOperationApi.getDate().toString() + " " +
			    lastOperationApi.getAmount() + " " + lastOperationApi.getBalance());
	}
	

}
