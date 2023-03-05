package com.ghdevlab.bankmanagingsystem.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySenderId(Long senderId);
    List<Transaction> findByReceiverId(Long receiverId);
}
