package com.jpmc.midascore.BalanceController;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.repository.UserRepository;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final UserRepository userRepository;

    public BalanceController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public BigDecimal getBalance(@RequestParam Long userId) {
        Optional<UserRecord> user = userRepository.findById(userId);
        return user.map(UserRecord::getBalance).orElse(BigDecimal.ZERO);
    }
}

