package com.jpmc.midascore.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users") // Avoids conflict with reserved SQL keywords
public class UserRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ensures auto-increment
    private Long id;

    @Column(nullable = false, unique = true) // Ensures unique usernames
    private String name;

    @Column(nullable = false)
    private BigDecimal balance; // Changed from float to BigDecimal

    // Default constructor required by JPA
    protected UserRecord() {}

    // Constructor for initializing new users
    public UserRecord(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("UserRecord[id=%d, name='%s', balance=%.2f]", id, name, balance);
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    // Setter for updating balance
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

