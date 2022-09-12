package com.example.brokebroker.repositories;

import com.example.brokebroker.model.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {

    List<Wallet> findByOwnerId(long ownerId);
    Wallet findByOwnerIdAndCoinApiName(Long ownerId, String apiName);
}