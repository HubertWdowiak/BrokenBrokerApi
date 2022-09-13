package com.example.brokebroker.controllers;

import com.example.brokebroker.model.Wallet;
import com.example.brokebroker.model.api.ApiPlayerRequest;
import com.example.brokebroker.model.api.PlayerRankingApi;
import com.example.brokebroker.repositories.PlayerRepository;
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
import java.util.Objects;

@RestController
public class PlayerController {

    private final PlayerRepository repository;
    private final WalletRepository walletRepository;
    private final CoinService coinService;
    private final PlayerService playerService;
    private final HttpHeaders responseHeaders;

    PlayerController(PlayerRepository repository, WalletRepository walletRepository, CoinService coinService,
                     PlayerService playerService) {
        this.repository = repository;
        this.walletRepository = walletRepository;
        this.coinService = coinService;
        this.playerService = playerService;
        this.responseHeaders = new HttpHeaders();
        this.responseHeaders.add("Access-Control-Allow-Origin", "*");
        this.responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
        this.responseHeaders.add("Access-Control-Allow-Headers", "Origin, Content-Type, X-Auth-Token");
    }

    /**
     * Method allows to register new user.
     */
    @PostMapping("Player/register")
    public ResponseEntity<String> register(@RequestBody ApiPlayerRequest apiPlayerRequest) {
        playerService.registerNewPlayer(apiPlayerRequest.getLogin(), apiPlayerRequest.getPassword());
        return ResponseEntity.ok().headers(responseHeaders).body("registered");
    }
    /**
     * Method allows to list ranking of all users and their $ worth.
     */
    @GetMapping("Player/ranking")
    public ResponseEntity<List<PlayerRankingApi>> ranking() {
        return ResponseEntity.ok().headers(responseHeaders).body(playerService.getUserRanking());
    }

    /**
     * Method allows to get player $ worth based on current market prices
     */
    @GetMapping("Player/balance")
    public ResponseEntity<Double> getUserBalance() {
        Map<String, Double> prices = coinService.getAllPrices();
        double balance = 0;
        for (Wallet wallet : walletRepository.findByOwnerId(1L)) { // TODO change id
            balance += wallet.getBalance() * prices.get(wallet.getCoin().getApiName());
        }
        return ResponseEntity.ok().headers(responseHeaders).body(balance);
    }

    /**
     * Method allows to get current user rank in player $ worth ranking
     */
    @GetMapping("Player/rank")
    public ResponseEntity<Integer> getUserRank() {
        int value = 1;
        List<PlayerRankingApi> ranking = playerService.getUserRanking();
        for(int i=0; i<ranking.size(); i++){
            if(Objects.equals(ranking.get(i).getName(), repository.findById(1L).getLogin())) { // TODO change id
                value= i + 1;
                break;
            }
        }
        return ResponseEntity.ok().headers(responseHeaders).body(value);
    }
}
