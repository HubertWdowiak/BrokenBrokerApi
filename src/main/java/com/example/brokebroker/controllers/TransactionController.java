package com.example.brokebroker.controllers;

import com.example.brokebroker.model.Transaction;
import com.example.brokebroker.model.Wallet;
import com.example.brokebroker.model.api.ApiTransaction;
import com.example.brokebroker.model.api.ApiTransactionRequest;
import com.example.brokebroker.repositories.TransactionRepository;
import com.example.brokebroker.repositories.WalletRepository;
import com.example.brokebroker.services.CoinService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class TransactionController {
    TransactionRepository transactionRepository;
    WalletRepository walletRepository;
    CoinService coinService;

    TransactionController(TransactionRepository transactionRepository, WalletRepository walletRepository, CoinService coinService){
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.coinService = coinService;
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<ApiTransaction>> getUserTransactions(){
        List<ApiTransaction> userTransactions = new ArrayList<>();
        for (Transaction transaction: transactionRepository.findByWalletOwnerId(1L)) { //TODO change id
            userTransactions.add(new ApiTransaction(transaction.getId(),
                    transaction.getWallet().getCoin().getApiName(),
                    transaction.getCoins(),
                    transaction.getCoinPrice(),
                    transaction.getTime()));
        }
        userTransactions.sort(Comparator.comparing(ApiTransaction::getTime).reversed());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin", "*");
        responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
        responseHeaders.add("Access-Control-Allow-Headers", "Origin, Content-Type, X-Auth-Token");

        return ResponseEntity.ok().headers(responseHeaders).body(userTransactions);
    }

    @PostMapping("Transaction/make/")
    public ResponseEntity<Transaction> makeTransaction(@RequestBody ApiTransactionRequest apiTransactionRequest){
        String coinName = apiTransactionRequest.getCoinName();
        double amount = apiTransactionRequest.getAmount(); // TODO change ID
        double price = coinService.getUSDPrice(coinName);
        double finalAmount = 0;
        double finalWorth;
        Wallet wallet = walletRepository.findByOwnerIdAndCoinApiName(1L, coinName);
        Wallet walletUSD = walletRepository.findByOwnerIdAndCoinApiName(1L, "usdc-usd-coin");

        if(amount != 0.0){
            if(amount > 0){
                finalWorth = Math.max(Math.min(amount*price, walletUSD.getBalance()), 0);
                finalAmount = finalWorth/price;
                wallet.setBalance((float) (wallet.getBalance() + finalAmount));
                walletUSD.setBalance((float) (walletUSD.getBalance() - finalWorth));
            } else if(amount < 0){
                finalAmount = Math.max(Math.min(-amount, wallet.getBalance()), 0);
                wallet.setBalance((float) (wallet.getBalance() - finalAmount));
                walletUSD.setBalance((float) (walletUSD.getBalance() + price * finalAmount));
            }
            if (finalAmount != 0){
                Transaction transaction;
                if(amount > 0) {
                    transaction = new Transaction(wallet, finalAmount, price, LocalDateTime.now());
                } else {
                    transaction = new Transaction(wallet, -finalAmount, price, LocalDateTime.now());
                }
                transactionRepository.save(transaction);
                walletRepository.save(wallet);
                walletRepository.save(walletUSD);
            }
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin", "*");
        responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
        responseHeaders.add("Access-Control-Max-Age", "3600");
        responseHeaders.add("Access-Control-Allow-Headers", "Origin, Content-Type, X-Auth-Token");

        return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).build();
    }
}
