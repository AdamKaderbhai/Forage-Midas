package com.jpmc.midascore.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jpmc.midascore.entity.TransactionRecord;

@Service
public class IncentiveService {

    private final RestTemplate restTemplate;

    public IncentiveService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double getIncentive(TransactionRecord transaction) {
        String url = "http://localhost:8080/incentive";

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request
        HttpEntity<TransactionRecord> request = new HttpEntity<>(transaction, headers);

        // Make the POST request
        ResponseEntity<IncentiveResponse> response = restTemplate.postForEntity(url, request, IncentiveResponse.class);

        // Return the incentive amount
        return response.getBody() != null ? response.getBody().getAmount() : 0.0;
    }
}

// DTO class to store API response
class IncentiveResponse {
    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}