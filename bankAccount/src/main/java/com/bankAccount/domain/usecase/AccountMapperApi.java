package com.bankAccount.domain.usecase;

import com.bankAccount.domain.entities.Account;
import com.bankAccount.domain.entities.Operation;

public class AccountMapperApi {

	public static AccountApi mapAccountToAccountApi(Account account) {
		return new AccountApi(account.getId(), account.getInfoAccount(), account.getBalance());
	}

	public static OperationApi mapOperationToOperationApi(Operation operation) {
		return new OperationApi(operation.getType(), operation.getDate(), operation.getAmount(),0);
	}

}
