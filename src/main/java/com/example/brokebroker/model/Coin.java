package com.example.brokebroker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column
    public String apiName;

    @JsonIgnore
    @OneToMany(mappedBy = "coin")
    public List<Wallet> wallets;
    public Coin(String name){
        apiName = name;
    }

    public Coin() {
    }

    @Override
    public String toString() {
        return String.format(
                "Coin[id=%d, name='%s']",
                id, apiName);
    }
}
