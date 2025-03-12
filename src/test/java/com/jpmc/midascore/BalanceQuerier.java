package com.jpmc.midascore;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.jpmc.midascore.foundation.Balance;

@Component
public class BalanceQuerier {
    private final RestTemplate restTemplate;

    public BalanceQuerier(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public Balance query(Long userId) {
        String url = "http://localhost:33400/balance?userId=" + userId;
        try {
            return restTemplate.getForObject(url, Balance.class);
        } catch (RestClientException e) {
            System.err.println("Error querying balance for userId: " + userId + " - " + e.getMessage());
            return new Balance(); // Return a default Balance object to avoid null issues
        }
    }
}

