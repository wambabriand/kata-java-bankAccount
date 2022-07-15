package com.bankAccount.domain.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
	private Long id;
	private LocalDateTime date;
	private String type;
	private int amount;
	private Account account;
}
