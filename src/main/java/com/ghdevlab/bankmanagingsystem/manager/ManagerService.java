package com.ghdevlab.bankmanagingsystem.manager;

import com.ghdevlab.bankmanagingsystem.worker.Worker;
import com.ghdevlab.bankmanagingsystem.worker.WorkerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final WorkerRepository workerRepository;


    @Autowired
    public ManagerService(ManagerRepository managerRepository, WorkerRepository workerRepository) {
        this.managerRepository = managerRepository;
        this.workerRepository = workerRepository;

    }


    public List<Manager> getManagers() {
        return managerRepository.findAll();
    }

    public Manager getManagerById(Long id) {
        return managerRepository.findById(id).orElseThrow(() -> new IllegalStateException("Manager with id " + id + " does not exist"));
    }

    public Manager createManager(Manager manager) {
        return managerRepository.save(manager);
    }

    @Transactional
    public Manager updateManager(Long id, String firstName, String lastName, String email, String password) {
        Manager managerToUpdate = managerRepository.findById(id).orElseThrow(() -> new IllegalStateException("Manager with id " + id + " does not exist"));
        if (firstName != null && firstName.length() > 0 && !Objects.equals(managerToUpdate.getFirstName(), firstName)) {
            managerToUpdate.setFirstName(firstName);
        }
        if (lastName != null && lastName.length() > 0 && !Objects.equals(managerToUpdate.getLastName(), lastName)) {
            managerToUpdate.setLastName(lastName);
        }
        if (email != null && email.length() > 0 && !Objects.equals(managerToUpdate.getEmail(), email)) {
            managerToUpdate.setEmail(email);
        }
        if (password != null && password.length() > 0 && !Objects.equals(managerToUpdate.getPassword(), password)) {
            managerToUpdate.setPassword(password);
        }


        return managerRepository.save(managerToUpdate);
    }

    public void deleteManager(Long id) {
        Manager manager = managerRepository.findById(id).orElseThrow(() -> new IllegalStateException("Manager with id " + id + " does not exist"));
        managerRepository.delete(manager);
    }

    public Manager assignWorkerToManager(Long managerId, Long workerId) {
        Manager manager = managerRepository.findById(managerId).orElseThrow(() -> new IllegalStateException("Manager with id " + managerId + " does not exist"));
        Worker worker = workerRepository.findById(workerId).orElseThrow(() -> new IllegalStateException("Worker with id " + workerId + " does not exist"));
        manager.addWorker(worker);
        worker.setManager(manager);
        workerRepository.save(worker);
        return managerRepository.save(manager);
    }


}
