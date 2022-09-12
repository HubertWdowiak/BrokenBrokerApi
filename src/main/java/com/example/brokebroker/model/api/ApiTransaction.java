package com.example.brokebroker.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiTransaction {
    long id;
    String coinName;
    double coins;
    double coinPrice;
    LocalDateTime time;
}
