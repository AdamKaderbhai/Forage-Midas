package com.jpmc.midascore.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.repository.TransactionRepository;
import com.jpmc.midascore.repository.UserRepository;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private IncentiveService incentiveService; // Inject IncentiveService

    @Transactional
    public boolean processTransaction(Long senderId, Long recipientId, BigDecimal amount) {
        // Log all users before processing the transaction
        logAllUsers();

        // Fetch sender and recipient
        Optional<UserRecord> senderOpt = userRepository.findById(senderId);
        Optional<UserRecord> recipientOpt = userRepository.findById(recipientId);

        // Validate users
        if (senderOpt.isEmpty() || recipientOpt.isEmpty()) {
            System.out.println("ERROR: Sender or recipient not found!");
            return false; // Invalid sender or recipient
        }

        UserRecord sender = senderOpt.get();
        UserRecord recipient = recipientOpt.get();

        // Check if sender has enough balance
        if (sender.getBalance().compareTo(amount) < 0) {
            System.out.println("ERROR: Insufficient funds for sender ID: " + senderId);
            return false;
        }

        // Deduct from sender
        sender.setBalance(sender.getBalance().subtract(amount));

        // Create transaction object
        TransactionRecord transaction = new TransactionRecord(sender, recipient, amount);

        // Get incentive amount from API
        double incentiveAmount = incentiveService.getIncentive(transaction);
        System.out.println("Received Incentive Amount: " + incentiveAmount);

        // Add amount + incentive to recipient
        recipient.setBalance(recipient.getBalance().add(amount).add(BigDecimal.valueOf(incentiveAmount)));

        // Save updated balances
        System.out.println("Saving Sender Balance: " + sender.getBalance());
        userRepository.save(sender);
        
        System.out.println("Saving Recipient Balance: " + recipient.getBalance());
        userRepository.save(recipient);

        // Save transaction record
        transactionRepository.save(transaction);
        System.out.println("Transaction recorded successfully!");

        // Log Wilbur's balance for debugging
        logWilburBalance();

        return true;
    }

    public void logAllUsers() {
        List<UserRecord> users = userRepository.findAll();
        if (users.isEmpty()) {
            System.out.println("No users found in the database!");
        } else {
            System.out.println("Users in database:");
            for (UserRecord user : users) {
                System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + ", Balance: " + user.getBalance());
            }
        }
    }

    public void logWaldorfBalance() {
        System.out.println("Querying Waldorf’s balance...");
        Optional<UserRecord> waldorfOpt = userRepository.findByName("waldorf"); // Ensure proper case sensitivity
        if (waldorfOpt.isPresent()) {
            UserRecord waldorf = waldorfOpt.get();
            System.out.println("Waldorf found! Balance: " + waldorf.getBalance());
        } else {
            System.out.println("ERROR: Waldorf not found in database!");
        }
    }

    public void logWilburBalance() {
        System.out.println("Querying Wilbur’s balance...");
        Optional<UserRecord> wilburOpt = userRepository.findByName("wilbur"); // Ensure proper case sensitivity

        if (wilburOpt.isPresent()) {
            UserRecord wilbur = wilburOpt.get();
            System.out.println("✅ Wilbur's final balance: " + wilbur.getBalance());
        } else {
            System.out.println("❌ Wilbur not found in database!");
        }
    }
}

