package com.example.brokebroker.controllers;

import com.example.brokebroker.model.Wallet;
import com.example.brokebroker.model.api.ApiPlayerRequest;
import com.example.brokebroker.model.api.PlayerRankingApi;
import com.example.brokebroker.repositories.CoinRepository;
import com.example.brokebroker.repositories.PlayerRepository;
import com.example.brokebroker.repositories.TransactionRepository;
import com.example.brokebroker.repositories.WalletRepository;
import com.example.brokebroker.services.CoinService;
import com.example.brokebroker.services.PlayerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PlayerController {

    PlayerRepository repository;
    TransactionRepository transactionRepository;
    CoinRepository coinRepository;
    WalletRepository walletRepository;

    CoinService coinService;
    PlayerService playerService;

    PlayerController(PlayerRepository repository, TransactionRepository transactionRepository,
                     WalletRepository walletRepository, CoinRepository coinRepository, CoinService coinService, PlayerService playerService) {
        this.repository = repository;
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.coinRepository = coinRepository;
        this.coinService = coinService;
        this.playerService = playerService;
    }

    @PostMapping("Player/register")
    public ResponseEntity<String> register(@RequestBody ApiPlayerRequest apiPlayerRequest) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin", "*");
        responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
        responseHeaders.add("Access-Control-Allow-Headers", "Origin, Content-Type, X-Auth-Token");
        playerService.registerNewPlayer(apiPlayerRequest.getLogin(), apiPlayerRequest.getPassword());
        return ResponseEntity.ok().headers(responseHeaders).body("");
    }

    @GetMapping("Player/ranking")
    public ResponseEntity<List<PlayerRankingApi>> ranking() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin", "*");
        responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
        responseHeaders.add("Access-Control-Allow-Headers", "Origin, Content-Type, X-Auth-Token");

        return ResponseEntity.ok().headers(responseHeaders).body(playerService.getUserRanking());
    }

    @GetMapping("Player/balance")
    public ResponseEntity<Double> getUserBalance() {
        Map<String, Double> prices = coinService.getAllPrices();
        double balance = 0;
        for (Wallet wallet : walletRepository.findByOwnerId(1l)) { // TODO change id
            balance += wallet.getBalance() * prices.get(wallet.getCoin().getApiName());
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin", "*");
        responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
        responseHeaders.add("Access-Control-Allow-Headers", "Origin, Content-Type, X-Auth-Token");
        return ResponseEntity.ok().headers(responseHeaders).body(balance);
    }

    @GetMapping("Player/rank")
    public ResponseEntity<Integer> getUserRank() {
        int value = 1;
        List<PlayerRankingApi> ranking = playerService.getUserRanking();
        for(int i=0; i<ranking.size(); i++){
            if(ranking.get(i).getName() == repository.findById(1l).getLogin()) { // TODO change id
                value= i + 1;
                break;
            }
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin", "*");
        responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
        responseHeaders.add("Access-Control-Allow-Headers", "Origin, Content-Type, X-Auth-Token");
        return ResponseEntity.ok().headers(responseHeaders).body(value);
    }
}
