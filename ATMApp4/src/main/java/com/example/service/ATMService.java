package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.model.User;
import com.example.model.Transaction;
import com.example.repository.UserRepository;
import com.example.repository.TransactionRepository;

@Service
public class ATMService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TransactionRepository txnRepo;

    public User registerOrLogin(String email, String mobile) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setMobile(mobile);
            user.setBalance(0.0);
            userRepo.save(user);
        }
        return user;
    }

    public double deposit(String email, double amount) {
        User user = userRepo.findByEmail(email);
        user.setBalance(user.getBalance() + amount);
        userRepo.save(user);
        txnRepo.save(new Transaction(null, email, "Deposit", amount, null));
        return user.getBalance();
    }

    public double withdraw(String email, double amount) {
        User user = userRepo.findByEmail(email);
        if (user.getBalance() >= amount) {
            user.setBalance(user.getBalance() - amount);
            userRepo.save(user);
            txnRepo.save(new Transaction(null, email, "Withdraw", amount, null));
            return user.getBalance();
        } else {
            throw new RuntimeException("Insufficient balance!");
        }
    }

    public double getBalance(String email) {
        return userRepo.findByEmail(email).getBalance();
    }
}
