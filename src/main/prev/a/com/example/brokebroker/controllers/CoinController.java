//package com.example.brokebroker.controllers;
//
//import com.example.brokebroker.model.User;
//import com.example.brokebroker.repositories.UserRepository;
//import com.example.brokebroker.repositories.WalletRepository;
//import com.example.brokebroker.services.CoinService;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class CoinController {
//    private final CoinService coinService;
//    private final JdbcTemplate jdbcTemplate;
//    private final UserRepository userRepository;
//    private final WalletRepository walletRepository;
//
//    public CoinController(CoinService coinService, JdbcTemplate jdbcTemplate, UserRepository userRepository, WalletRepository walletRepository) {
//        this.coinService = coinService;
//        this.jdbcTemplate = jdbcTemplate;
//        this.userRepository = userRepository;
//        this.walletRepository = walletRepository;
//    }
//
//
//    @GetMapping("/")
//    public String index(){
//        return coinService.getUSDPrice("btc-bitcoin");
//    }
//
//    @GetMapping("/test")
//    public List<Map<String, Object>> index2(){
//        return jdbcTemplate.queryForList("SELECT * FROM PERSON");
//    }
//
//    @GetMapping("/test2")
//    public Iterable<User> inde3(){
//        return userRepository.findAll();
//    }
//
////    @GetMapping("/test3")
////    public Iterable<User> ind2e3(){
//////        return walletRepository.findWalletByOwner();
////    }
//
//}
