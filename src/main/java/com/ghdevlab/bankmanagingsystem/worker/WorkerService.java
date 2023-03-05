package com.ghdevlab.bankmanagingsystem.worker;

import com.ghdevlab.bankmanagingsystem.client.Client;
import com.ghdevlab.bankmanagingsystem.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository, ClientRepository clientRepository) {
        this.workerRepository = workerRepository;
        this.clientRepository = clientRepository;
    }

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }
    public Worker getWorkerById(Long id) {
        return workerRepository.findById(id).orElseThrow(() -> new IllegalStateException("Worker with id " + id + " does not exist"));
    }

    public Worker createWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    public Worker updateWorker(Long id, String firstName, String lastName, String email, String password) {
        Worker workerToUpdate = workerRepository.findById(id).orElseThrow(() -> new IllegalStateException("Worker with id " + id + " does not exist"));
        if(firstName != null && firstName.length() > 0 && !workerToUpdate.getFirstName().equals(firstName)) {
            workerToUpdate.setFirstName(firstName);
        }
        if(lastName != null && lastName.length() > 0 && !workerToUpdate.getLastName().equals(lastName)) {
            workerToUpdate.setLastName(lastName);
        }
        if(email != null && email.length() > 0 && !workerToUpdate.getEmail().equals(email)) {
            workerToUpdate.setEmail(email);
        }
        if(password != null && password.length() > 0 && !workerToUpdate.getPassword().equals(password)) {
            workerToUpdate.setPassword(password);
        }

        return workerRepository.save(workerToUpdate);
    }



    public void deleteWorker(Long id) {
        Worker worker = workerRepository.findById(id).orElseThrow(() -> new IllegalStateException("Worker with id " + id + " does not exist"));
        workerRepository.delete(worker);
    }

    public void assignClientToWorker(Long workerId, Long clientId) {
        Worker worker = workerRepository.findById(workerId).orElseThrow(() -> new IllegalStateException("Worker with id " + workerId + " does not exist"));
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new IllegalStateException("Client with id " + clientId + " does not exist"));
        worker.getClients().add(client);
        client.setWorker(worker);
        workerRepository.save(worker);
        clientRepository.save(client);
    }
}
