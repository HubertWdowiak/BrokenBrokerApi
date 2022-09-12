package com.example.brokebroker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Wallet {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
    public List<Transaction> transactions;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Player owner;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coin_id")
    private Coin coin;

    @Column
    private Float balance;

    public Wallet() {
    }

    public Wallet(Float balance, Coin coin, Player owner){
        this.balance = balance;
        this.coin = coin;
        this.transactions = new ArrayList<>();
        this.owner = owner;
    }
}
