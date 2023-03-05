package com.ghdevlab.bankmanagingsystem.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void addNewTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id){
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.orElse(null);
    }

    public List<Transaction> getOutgoingTransactions(Long clientId) {
        System.out.println("Hello");
        return transactionRepository.findBySenderId(clientId);
    }

    public List<Transaction> getIncomingTransactions(Long clientId) {
        return transactionRepository.findByReceiverId(clientId);
    }

    public List<Transaction> getTransactionsByClientId(Long clientId) {
        List<Transaction> outgoingTransactions = transactionRepository.findBySenderId(clientId);
        List<Transaction> incomingTransactions = transactionRepository.findByReceiverId(clientId);
        outgoingTransactions.addAll(incomingTransactions);
        return outgoingTransactions;
    }

}
