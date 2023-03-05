package com.ghdevlab.bankmanagingsystem.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {


    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }



    @GetMapping
    public List<Worker> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    @GetMapping("/{id}")
    public Worker getWorkerById(@PathVariable Long id) {
        return workerService.getWorkerById(id);
    }

    @PostMapping
    public Worker createWorker(@RequestBody Worker worker) {
        return workerService.createWorker(worker);
    }


    @PutMapping("/{id}")
    public Worker updateWorker(@PathVariable Long id, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String email, @RequestParam(required = false) String password) {
        return workerService.updateWorker(id, firstName, lastName, email, password);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorker(@PathVariable Long id) {
        workerService.deleteWorker(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{workerId}/assign_client/{clientId}")
    public void assignClientToWorker(@PathVariable Long workerId, @PathVariable Long clientId) {
        workerService.assignClientToWorker(workerId, clientId);
    }


}
