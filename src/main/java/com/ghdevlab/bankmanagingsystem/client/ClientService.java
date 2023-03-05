package com.ghdevlab.bankmanagingsystem.client;

import com.ghdevlab.bankmanagingsystem.transaction.Transaction;
import com.ghdevlab.bankmanagingsystem.transaction.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final TransactionService transactionService;

    @Autowired
    public ClientService(ClientRepository clientRepository, TransactionService transactionService) {
        this.clientRepository = clientRepository;
        this.transactionService = transactionService;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    public Client getClientById(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> new IllegalStateException("client with id " + clientId + " does not exist"));
    }

    public Client addNewClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Long clientId) {
        boolean exists = clientRepository.existsById(clientId);
        if (!exists) {
            throw new IllegalStateException("client with id " + clientId + " does not exist");
        }
        clientRepository.deleteById(clientId);
    }

    @Transactional
    public Client updateClient(Long clientId, String firstName, String lastName, String email, String password) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new IllegalStateException("client with id " + clientId + " does not exist"));
        if(firstName != null && firstName.length() > 0 && !Objects.equals(client.getFirstName(), firstName)){
            client.setFirstName(firstName);
        }
        if(lastName != null && lastName.length() > 0 && !Objects.equals(client.getLastName(), lastName)){
            client.setLastName(lastName);
        }

        if (email != null && email.length() > 0 && !Objects.equals(client.getEmail(), email)) {
            client.setEmail(email);
        }
        if (password != null && password.length() > 0 && !Objects.equals(client.getPassword(), password)) {
            client.setPassword(password);
        }
       return clientRepository.save(client);
    }

    @Transactional
    public Client deposit(Long clientId, Double amount) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new IllegalStateException("client with id " + clientId + " does not exist"));
        client.setBalance(client.getBalance() + amount);
        Transaction transaction = new Transaction(client, "DEPOSIT", amount, new Date());
        transactionService.addNewTransaction(transaction);
        client.getIncomingTransactions().add(transaction);
        return clientRepository.save(client);
    }

    @Transactional
    public Client withdraw(Long clientId, Double amount) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new IllegalStateException("client with id " + clientId + " does not exist"));
        if(client.getBalance() < amount){
            throw new IllegalStateException("client with id " + clientId + " does not have enough money");
        }
        client.setBalance(client.getBalance() - amount);
        Transaction transaction = new Transaction(client, "WITHDRAW", amount, new Date());
        transactionService.addNewTransaction(transaction);
        client.getOutgoingTransactions().add(transaction);
        return clientRepository.save(client);
    }

    @Transactional
    public ResponseEntity<?> transfer(Long senderId, Long receiverId, Double amount){
        Client sender = clientRepository.findById(senderId).orElseThrow(() -> new IllegalStateException("client with id " + senderId + " does not exist"));
        Client receiver = clientRepository.findById(receiverId).orElseThrow(() -> new IllegalStateException("client with id " + receiverId + " does not exist"));
        if(sender.getBalance()<amount){
            throw new IllegalStateException("client with id " + senderId + " does not have enough money");
        }
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setType("TRANSFER");
        transaction.setAmount(amount);
        transaction.setDate(new Date());
        sender.getOutgoingTransactions().add(transaction);
        receiver.getIncomingTransactions().add(transaction);
        transactionService.addNewTransaction(transaction);
        clientRepository.save(sender);
        clientRepository.save(receiver);
        return ResponseEntity.ok().build();
    }
}
