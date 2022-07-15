package com.bankAccount.adapters;


import com.bankAccount.domain.entities.Account;
import com.bankAccount.domain.entities.Operation;

public class AccountAdapterMapper {

	public static AccountJpa mapAccountToAccountJpa(Account account) {
		return new AccountJpa(account.getId(), account.getInfoAccount(), account.getBalance(), null);
	}

	public static Account mapAccountJpaToAccount(AccountJpa accountJpa) {
		return new Account(accountJpa.getId(), accountJpa.getInfoAccount(), accountJpa.getBalance(), null);
	}
	

	public static OperationJpa mapOperationToOperationJpa(Operation operation) {
		return new OperationJpa(null, operation.getDate(),operation.getType(),
				operation.getAmount(), mapAccountToAccountJpa(operation.getAccount()));
	}
	
	public static Operation mapOperationJpaToOperation(OperationJpa operationJpa) {
		return new Operation(operationJpa.getId(), operationJpa.getDate(), operationJpa.getType(), 
				operationJpa.getAmount(), mapAccountJpaToAccount(operationJpa.getAccount()) );
	}

}
