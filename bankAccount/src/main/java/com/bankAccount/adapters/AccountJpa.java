package com.bankAccount.adapters;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountJpa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String infoAccount;
	private int balance;
	
	@OneToMany(mappedBy = "account")
	private List<OperationJpa> operations;
}
