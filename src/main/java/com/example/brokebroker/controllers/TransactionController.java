package com.example.brokebroker.controllers;

import com.example.brokebroker.model.Transaction;
import com.example.brokebroker.model.api.ApiTransaction;
import com.example.brokebroker.model.api.ApiTransactionRequest;
import com.example.brokebroker.services.TransactionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {
    private final TransactionService transactionService;
    private HttpHeaders responseHeaders;

    TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
        this.responseHeaders = new HttpHeaders();
        this.responseHeaders.add("Access-Control-Allow-Origin", "*");
        this.responseHeaders.add("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
        this.responseHeaders.add("Access-Control-Max-Age", "3600");
        this.responseHeaders.add("Access-Control-Allow-Headers", "Origin, Content-Type, X-Auth-Token");
    }

    /**
     * Method allows to all historical user transactions.
     */
    @GetMapping("/transactions")
    public ResponseEntity<List<ApiTransaction>> getUserTransactions(){
        return ResponseEntity.ok().headers(responseHeaders).body(transactionService.getAllTransactions());
    }

    /**
     * Method allows create a new transaction and store it in database
     */
    @PostMapping("Transaction/make/")
    public ResponseEntity<Transaction> makeTransaction(@RequestBody ApiTransactionRequest apiTransactionRequest){
        transactionService.makeTransaction(apiTransactionRequest.getCoinName(), apiTransactionRequest.getAmount());
        return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).build();
    }
}
