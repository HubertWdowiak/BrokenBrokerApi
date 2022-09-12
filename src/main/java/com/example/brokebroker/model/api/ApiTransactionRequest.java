package com.example.brokebroker.model.api;

import lombok.Data;

@Data
public class ApiTransactionRequest {
    Double amount;
    String coinName;
}
