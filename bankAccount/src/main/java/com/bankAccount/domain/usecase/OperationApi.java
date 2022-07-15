package com.bankAccount.domain.usecase;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OperationApi {
	private String type;
	private LocalDateTime date;
	private int amount;
	private int balance;
	
	@Override
	public String toString() {
		return type + " "+ date.toString() + " " + amount + " " + balance;
	}
}
