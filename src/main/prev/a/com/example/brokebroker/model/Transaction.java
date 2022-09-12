//package com.example.brokebroker.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import lombok.Data;
//
//import javax.persistence.*;
//
//@Entity
//@Data
//public class Transaction {
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private Long id;
//
//    @JsonBackReference
//    @ManyToOne(cascade = {CascadeType.ALL})
//    private User user;
//
//    @JsonBackReference
//    @ManyToOne(cascade = {CascadeType.ALL})
//    private Wallet wallet;
//
//    private float coins;
//    private float coinPrice;
//    private long time;
//
//    public Transaction(User user, Wallet wallet, float coins, float coinPrice, long time){
//        this.user = user;
//        this.wallet = wallet;
//        this.coinPrice = coinPrice;
//        this.coins = coins;
//        this.time = time;
//    }
//
//    protected Transaction() {}
//}
