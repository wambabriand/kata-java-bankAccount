package com.bankAccount.domain.usecase;

import com.bankAccount.domain.entities.Account;

public class Mapper {

	public static AccountApi mapAccountToAccountApi(Account account) {
		return new AccountApi(account.getId(), account.getInfoAccount(), account.getBalance());
	}

}
