package com.jpmc.midascore.foundation;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Balance {
    
    private BigDecimal amount;  // Use BigDecimal for financial values

    // Default constructor (Required for JSON deserialization)
    public Balance() {
        this.amount = BigDecimal.ZERO;
    }

    // Constructor with annotation
    @JsonCreator
    public Balance(@JsonProperty("amount") BigDecimal amount) {
        this.amount = amount;
    }

    // Getter and Setter (Required for Jackson to map values)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "amount=" + amount +
                '}';
    }
}

