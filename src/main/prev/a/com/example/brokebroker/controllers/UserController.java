//package com.example.brokebroker.controllers;
//
//import com.example.brokebroker.model.Transaction;
//import com.example.brokebroker.model.User;
//import com.example.brokebroker.model.Wallet;
//import com.example.brokebroker.repositories.TransactionRepository;
//import com.example.brokebroker.repositories.UserRepository;
//import com.example.brokebroker.repositories.WalletRepository;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//public class UserController {
//
//    UserRepository repository;
//    TransactionRepository transactionRepository;
//    WalletRepository walletRepository;
//
//    UserController(UserRepository repository, TransactionRepository transactionRepository,
//                   WalletRepository walletRepository){
//        this.repository = repository;
//        this.transactionRepository = transactionRepository;
//        this.walletRepository = walletRepository;
//    }
//
//    @GetMapping("/user/{id}")
//    public Optional<User> index(@PathVariable Long id){
//        return repository.findById(id);
//    }
//
//    @GetMapping("/user/{id}/wallets")
//    public List<Wallet> getUserWallets(@PathVariable Long id){
//        return repository.findById(id).get().getWallets();
//    }
//
//    @GetMapping("/user/{id}/transactions")
//    public List<Transaction> getUserTransactions(@PathVariable Long id){
//        return transactionRepository.findByUser_Id(id);
//    }
//
//    @GetMapping("/user/{id}/trade/{coin}/{amount}")
//    public List<Transaction> getUserTransactions(@PathVariable Long id, @PathVariable String coin, @PathVariable float amount){
//        User user = repository.findById(id).get();
//        Wallet wallet = walletRepository.findByOwnerAndCoinId(user, Wallet.Coin.valueOf(coin).Id);
//        transactionRepository.save(new Transaction(user, wallet, amount, 30, System.currentTimeMillis()));
//        wallet.setCoins(wallet.getCoins() + amount);
//        return wallet.transactions;
//    }
//
//}
