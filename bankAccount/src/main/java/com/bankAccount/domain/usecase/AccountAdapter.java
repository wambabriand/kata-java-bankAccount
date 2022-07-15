package com.bankAccount.domain.usecase;

import java.util.List;

import com.bankAccount.domain.entities.Account;
import com.bankAccount.domain.entities.Operation;

public interface AccountAdapter {

	Account saveAccount(Account account);

	Operation updateAccountAndSaveOperation(Account account, Operation operation);

	Account findAccountById(Long idAccount);

	List<Operation> findOperationsByAccountId(Long idAccount);

}
