package com.example.brokebroker.repositories;

import com.example.brokebroker.model.Coin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinRepository extends CrudRepository<Coin, Long> {
    List<Coin> findAll();
}