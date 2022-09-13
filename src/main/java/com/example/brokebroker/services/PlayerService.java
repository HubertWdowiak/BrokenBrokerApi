package com.example.brokebroker.services;

import com.example.brokebroker.model.Coin;
import com.example.brokebroker.model.Player;
import com.example.brokebroker.model.Wallet;
import com.example.brokebroker.model.api.PlayerRankingApi;
import com.example.brokebroker.repositories.CoinRepository;
import com.example.brokebroker.repositories.PlayerRepository;
import com.example.brokebroker.repositories.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerService {

    private final CoinService coinService;
    private final CoinRepository coinRepository;
    private final PlayerRepository repository;
    private final WalletRepository walletRepository;

    public PlayerService(CoinService coinService, PlayerRepository playerRepository, CoinRepository coinRepository, WalletRepository walletRepository){
        this.coinService = coinService;
        this.repository = playerRepository;
        this.coinRepository = coinRepository;
        this.walletRepository = walletRepository;
    }

    /**
     * Method allows to get user $ worth ranking
     */
    public List<PlayerRankingApi> getUserRanking(){
        Map<String, Double> prices = coinService.getAllPrices();
        List<PlayerRankingApi> ranking = new ArrayList<>();
        for (Player player : repository.findAll()) {
            PlayerRankingApi playerApi = new PlayerRankingApi(player.getLogin(), 0);
            for (Wallet wallet : player.getWallets()) {
                playerApi.setBalance(playerApi.getBalance() +
                        wallet.getBalance() * prices.get(wallet.getCoin().getApiName())
                );
            }
            ranking.add(playerApi);
        }
        ranking.sort(Comparator.comparing(PlayerRankingApi::getBalance).reversed());
        return ranking;
    }

    /**
     * Method allows to register new player and grant him starting assets.
     */
    public void registerNewPlayer(String login, String password) {
        Player player = new Player(login, password);
        repository.save(player);

        for (Coin coin : coinRepository.findAll()) {
            if(coin.getApiName().equals("usdc-usd-coin")){
                walletRepository.save(new Wallet(1000f, coin, player));
            }
            else{
                walletRepository.save(new Wallet(0f, coin, player));
            }
        }
    }
}
