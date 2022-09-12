package com.example.brokebroker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//public class BrokeBrokerApplication implements CommandLineRunner {
public class BrokeBrokerApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BrokeBrokerApplication.class);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BrokeBrokerApplication.class, args);
        System.out.println("Running............");
    }
    //    private final CoinRepository coinRepository;
//    private PlayerService playerService;
//    private PlayerRepository playerRepository;
//    private WalletRepository walletRepository;
//    public BrokeBrokerApplication(CoinRepository coinRepository, PlayerService playerService,
//                                  PlayerRepository playerRepository, WalletRepository walletRepository) {
//        this.coinRepository = coinRepository;
//        this.playerService = playerService;
//        this.playerRepository = playerRepository;
//        this.walletRepository = walletRepository;
//    }
    
//    @Override
//    public void run(String... args) {
//        walletRepository.deleteAll();
//        coinRepository.deleteAll();
//        Coin coinA = new Coin("btc-bitcoin");
//        Coin coinB = new Coin("eth-ethereum");
//        Coin coinC = new Coin("bnb-binance-coin");
//        Coin coinD = new Coin("usdc-usd-coin");
//        coinRepository.saveAll(Arrays.asList(coinA, coinB, coinC, coinD));
//        playerRepository.deleteAll();
//        playerService.registerNewPlayer("A", "A");
//        playerService.registerNewPlayer("B", "B");
//        playerService.registerNewPlayer("C", "C");
//    }
}
