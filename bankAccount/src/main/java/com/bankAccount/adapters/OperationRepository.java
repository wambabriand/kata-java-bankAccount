package com.bankAccount.adapters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<OperationJpa, Long>{

	List<OperationJpa> findByAccount_IdOrderByDateAsc(Long idAccount);
	
}
