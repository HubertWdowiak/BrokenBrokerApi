package com.example.brokebroker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Wallet wallet;

    @Column
    private double coins;

    @Column
    private double coinPrice;

    @Column
    private LocalDateTime time;

    public Transaction(Wallet wallet, double coins, double coinPrice, LocalDateTime time){
        this.wallet = wallet;
        this.coinPrice = coinPrice;
        this.coins = coins;
        this.time = time;
    }

    protected Transaction() {}
}
