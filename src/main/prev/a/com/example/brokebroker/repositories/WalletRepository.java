//package com.example.brokebroker.repositories;
//
//import com.example.brokebroker.model.User;
//import com.example.brokebroker.model.Wallet;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface WalletRepository extends CrudRepository<Wallet, Long> {
//
//    Wallet findWalletByOwner(User user);
//    Wallet findByOwnerAndCoinId(User owner, Long id);
//}