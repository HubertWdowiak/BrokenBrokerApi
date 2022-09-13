package com.example.brokebroker.services;

import com.example.brokebroker.model.Coin;
import com.example.brokebroker.repositories.CoinRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CoinService {

    private final CoinRepository coinRepository;
    private final RestTemplate restTemplate;

    public CoinService(RestTemplateBuilder restTemplateBuilder, CoinRepository coinRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.coinRepository = coinRepository;
    }

    /**
     * Method allows to fetch current market price of specific coin.
     */
    public Double getUSDPrice(String coinApiName) {
        String url = "https://api.coinpaprika.com/v1/tickers/" + coinApiName;
        String json = this.restTemplate.getForObject(url, String.class);
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Map<String, Map<String, Object>>> map = mapper.readValue(json, Map.class);
            return (Double) map.get("quotes").get("USD").get("price");
        }
        catch (JsonProcessingException exception){
            return 0D;
        }
    }

    /**
     * Method allows to fetch current market price of all coins.
     */
    public Map<String, Double> getAllPrices(){
        Map<String, Double> prices = new HashMap<>();
        for (Coin coin: coinRepository.findAll()) {
            prices.put(coin.getApiName(), getUSDPrice(coin.getApiName()));
        }
        return prices;
    }

}
