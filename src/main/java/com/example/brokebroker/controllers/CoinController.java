package com.example.brokebroker.controllers;

import com.example.brokebroker.model.Wallet;
import com.example.brokebroker.model.api.ApiPair;
import com.example.brokebroker.repositories.WalletRepository;
import com.example.brokebroker.services.CoinService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class CoinController {
    private final CoinService coinService;
    private final WalletRepository walletRepository;
    private final HttpHeaders responseHeaders;

    public CoinController(CoinService coinService, WalletRepository walletRepository) {
        this.coinService = coinService;
        this.walletRepository = walletRepository;
        this.responseHeaders = new HttpHeaders();
        this.responseHeaders.add("Access-Control-Allow-Origin", "*");
        this.responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
        this.responseHeaders.add("Access-Control-Allow-Headers", "Origin, Content-Type, X-Auth-Token, Mode");
    }

    /**
     * Method allows to list all coins, their prices and user balance
     */
    @GetMapping("/pairs")
    public ResponseEntity<List<ApiPair>> getAllPairs(){
        List<ApiPair> allPairs = new ArrayList<>();
        for (Wallet wallet : walletRepository.findByOwnerId(1L)) { // TODO change id
            float balance = wallet.getBalance();
            ApiPair pair = new ApiPair(wallet.getCoin().getApiName(),
                    balance,
                    (coinService.getUSDPrice(wallet.getCoin().getApiName())));
            allPairs.add(pair);
        }
        allPairs.sort(Comparator.comparing(ApiPair::getApiName));
        return ResponseEntity.ok().headers(responseHeaders).body(allPairs);
    }

}
