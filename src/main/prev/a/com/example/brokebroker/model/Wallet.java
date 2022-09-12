//package com.example.brokebroker.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Data
//public class Wallet {
//    public enum Coin {
//        BTC(1L), ETH(2L), BNB(3L), USDT(4L);
//
//        public final Long Id;
//
//        Coin(Long coinId){
//            this.Id = coinId;
//        }
//
//        public static String toString(Long id) {
//            for (Coin e : values()) {
//                if (e.Id.equals(id)) {
//                    return e.name();
//                }
//            }
//            return null;
//        }
//    }
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private Long id;
//
//    @JsonManagedReference
//    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "wallet")
//    public List<Transaction> transactions;
//    @JsonBackReference
//    @ManyToOne(cascade = {CascadeType.ALL})
//    private User owner;
//
//    private Long coinId;
//    private Float coins;
//
//    protected Wallet() {}
//    public Wallet(User owner, Long coinId) {
//        this.owner = owner;
//        this.coinId = coinId;
//        this.coins = (float) 0;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("%s=[%f]", Wallet.Coin.toString(coinId), coins);
//    }
//
//}
