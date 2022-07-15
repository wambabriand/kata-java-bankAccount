package com.bankAccount.adapters;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bankAccount.domain.entities.Account;
import com.bankAccount.domain.entities.Operation;
import com.bankAccount.domain.usecase.AccountAdapter;

@Component
public class AccountAdapterImpl implements AccountAdapter  {

	private AccountRepository accountRepository;
	
	private OperationRepository operationRepository;
	
	public AccountAdapterImpl(AccountRepository accountRepository, OperationRepository operationRepository) {
		this.accountRepository = accountRepository;
		this.operationRepository = operationRepository;
	}

	
	@Override
	public Account findAccountById(Long idAccount) {
		Optional<AccountJpa> accountJpa = accountRepository.findById(idAccount);
		return accountJpa.isPresent() ? AccountAdapterMapper.mapAccountJpaToAccount(accountJpa.get()) : null;
	}
	
	@Override
	public Account saveAccount(Account account) {
		AccountJpa accountJpa =  AccountAdapterMapper.mapAccountToAccountJpa(account);
		accountRepository.save(accountJpa);
		return  AccountAdapterMapper.mapAccountJpaToAccount(accountJpa);
	}
	
	@Override
	public Operation updateAccountAndSaveOperation(Account account, Operation operation) {
		OperationJpa operationJpa = AccountAdapterMapper.mapOperationToOperationJpa(operation);
		AccountJpa accountJpa = AccountAdapterMapper.mapAccountToAccountJpa(account);
		accountRepository.save(accountJpa);
		operationJpa = operationRepository.save(operationJpa);
		return AccountAdapterMapper.mapOperationJpaToOperation(operationJpa);
	}
	
	@Override
	public List<Operation> findOperationsByAccountId(Long idAccount) {
		List<OperationJpa> operationJpas = operationRepository.findByAccount_IdOrderByDateAsc(idAccount);
		System.out.println(operationJpas.size());
		return operationJpas.stream()
				.map(operationJpa -> AccountAdapterMapper.mapOperationJpaToOperation(operationJpa))
				.collect(Collectors.toList());
	}
}
