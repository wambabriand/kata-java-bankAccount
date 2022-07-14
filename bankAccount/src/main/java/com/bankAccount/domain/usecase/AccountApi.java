package com.bankAccount.domain.usecase;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AccountApi {
	private Long id;
	private String infoAccount;
	private int balance;
}
