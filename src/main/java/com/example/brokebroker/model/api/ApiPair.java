package com.example.brokebroker.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiPair {
    String apiName;
    float balance;
    double marketPrice;
}
