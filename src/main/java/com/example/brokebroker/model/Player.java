package com.example.brokebroker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String login;

    @Column
    private String password;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Wallet> wallets;

    public Player(String login, String password) {
        this.login = login;
        this.password = password;
        this.wallets = new ArrayList<>();
    }

    public Player() {

    }


    @Override
    public String toString() {
        return String.format(
                "Player[id=%d, login='%s', password='%s']",
                id, login, password);
    }

}
