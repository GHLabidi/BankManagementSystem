package com.ghdevlab.bankmanagingsystem.client;

import com.ghdevlab.bankmanagingsystem.transaction.Transaction;
import com.ghdevlab.bankmanagingsystem.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;
    private final TransactionService transactionService;

    @Autowired
    public ClientController(ClientService clientService, TransactionService transactionService) {
        this.clientService = clientService;
        this.transactionService = transactionService;
    }


    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.addNewClient(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String email, @RequestParam(required = false) String password) {
        return clientService.updateClient(id, firstName, lastName, email, password);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/deposit")
    public Client deposit(@PathVariable Long id, @RequestParam Double amount) {
        return clientService.deposit(id, amount);
    }

    @PutMapping("/{id}/withdraw")
    public Client withdraw(@PathVariable Long id, @RequestParam Double amount) {
        return clientService.withdraw(id, amount);
    }

    @PutMapping("/{id}/transfer")
    public ResponseEntity<?> transfer(@PathVariable Long id, @RequestParam Double amount, @RequestParam Long receiverId) {
        return clientService.transfer(id, receiverId, amount);
    }

    @GetMapping("/{id}/transactions")
    public List<Transaction> getTransactions(@PathVariable Long id) {
        return transactionService.getTransactionsByClientId(id);
    }

    @GetMapping("/{id}/outgoing-transactions")
    public List<Transaction> getOutgoingTransactions(@PathVariable Long id) {
        return transactionService.getOutgoingTransactions(id);
    }

    @GetMapping("/{id}/incoming-transactions")
    public List<Transaction> getIncomingTransactions(@PathVariable Long id) {
        return transactionService.getIncomingTransactions(id);
    }





}
