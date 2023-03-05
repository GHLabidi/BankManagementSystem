package com.ghdevlab.bankmanagingsystem.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/managers")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    public List<Manager> getAllManagers() {
        return managerService.getManagers();
    }

    @GetMapping("/{id}")
    public Manager getManagerById(@PathVariable Long id) {
        return managerService.getManagerById(id);
    }

    @PostMapping
    public Manager createManager(@RequestBody Manager manager) {
        return managerService.createManager(manager);
    }

    @PutMapping("/{id}")
    public Manager updateManager(@PathVariable Long id, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String email, @RequestParam(required = false) String password) {
        return managerService.updateManager(id, firstName, lastName, email, password);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteManager(@PathVariable Long id) {
        managerService.deleteManager(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping (path = "/{managerId}/assign_worker/{workerId}")
    public Manager addWorkerToManager(@PathVariable Long managerId, @PathVariable Long workerId) {
        return managerService.assignWorkerToManager(managerId, workerId);
    }

}
