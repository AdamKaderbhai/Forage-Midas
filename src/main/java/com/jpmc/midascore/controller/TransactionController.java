package com.jpmc.midascore.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpmc.midascore.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/process")
    public String processTransaction(@RequestBody Map<String, Object> request) {
        Long senderId = Long.valueOf(request.get("senderId").toString());
        Long recipientId = Long.valueOf(request.get("recipientId").toString());
        BigDecimal amount = new BigDecimal(request.get("amount").toString());

        boolean success = transactionService.processTransaction(senderId, recipientId, amount);
        return success ? "Transaction successful" : "Transaction failed";
    }
}
