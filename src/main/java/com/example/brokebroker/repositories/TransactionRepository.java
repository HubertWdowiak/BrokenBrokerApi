package com.example.brokebroker.repositories;

import com.example.brokebroker.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findByWalletOwnerId(Long id);

}