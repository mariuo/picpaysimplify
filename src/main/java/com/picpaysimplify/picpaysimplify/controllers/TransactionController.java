package com.picpaysimplify.picpaysimplify.controllers;

import com.picpaysimplify.picpaysimplify.domain.transaction.Transaction;
import com.picpaysimplify.picpaysimplify.dtos.TransactionDTO;
import com.picpaysimplify.picpaysimplify.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO dto) throws Exception {
        Transaction entity = this.transactionService.createTransaction(dto);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}
