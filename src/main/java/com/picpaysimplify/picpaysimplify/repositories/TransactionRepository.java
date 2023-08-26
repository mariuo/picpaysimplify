package com.picpaysimplify.picpaysimplify.repositories;

import com.picpaysimplify.picpaysimplify.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
