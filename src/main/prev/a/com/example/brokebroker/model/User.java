//package com.example.brokebroker.model;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import lombok.Getter;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//                @Entity
//@Getter
//@Table(name="users")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private Long id;
//    private String login;
//    private String password;
//
//    @JsonManagedReference
//    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "user")
//    private List<Transaction> transactions;
//
//    @JsonManagedReference
//    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "owner")
//    private List<Wallet> wallets;
//
//    protected User() {}
//
//    public User(String login, String password) {
//        this.login = login;
//        this.password = password;
//        this.wallets = new ArrayList<>();
//        for(Wallet.Coin coin: Wallet.Coin.values()){
//            wallets.add(new Wallet(this, coin.Id));
//        }
//    }
//
//    @Override
//    public String toString() {
//        return String.format(
//                "User[id=%d, login='%s', password='%s']",
//                id, login, password);
//    }
//
//}
