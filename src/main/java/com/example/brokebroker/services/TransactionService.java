package com.example.brokebroker.services;

import com.example.brokebroker.model.Transaction;
import com.example.brokebroker.model.Wallet;
import com.example.brokebroker.model.api.ApiTransaction;
import com.example.brokebroker.repositories.TransactionRepository;
import com.example.brokebroker.repositories.WalletRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TransactionService {
    private final CoinService coinService;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(CoinService coinService, WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.coinService = coinService;
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * Method allows to make and save a new transaction in database.
     */
    public void makeTransaction(String coinName, Double amount){

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
    }

    /**
     * Method allows to get a list of all user transactions
     */
    public List<ApiTransaction> getAllTransactions(){
        List<ApiTransaction> userTransactions = new ArrayList<>();
        for (Transaction transaction: transactionRepository.findByWalletOwnerId(1L)) { //TODO change id
            userTransactions.add(new ApiTransaction(transaction.getId(),
                    transaction.getWallet().getCoin().getApiName(),
                    transaction.getCoins(),
                    transaction.getCoinPrice(),
                    transaction.getTime()));
        }
        userTransactions.sort(Comparator.comparing(ApiTransaction::getTime).reversed());
        return userTransactions;
    }
}
