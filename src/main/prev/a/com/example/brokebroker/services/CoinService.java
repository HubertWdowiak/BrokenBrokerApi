//package com.example.brokebroker.services;
//
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class CoinService {
//
//    private final RestTemplate restTemplate;
//
//    public CoinService(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder.build();
//    }
//
//    public String getUSDPrice(String coinID){
//        String url = "https://api.coinpaprika.com/v1/tickers/" + coinID;
//        return this.restTemplate.getForObject(url, String.class);
//    }
//
//}
