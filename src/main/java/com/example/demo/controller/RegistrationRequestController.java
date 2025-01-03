package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.RegistrationRequestAgent;
import com.example.demo.services.RegistrationRequestAgentService;


@RestController
@RequestMapping("/api/back/registration-requests")
public class RegistrationRequestController {

    @Autowired
    private RegistrationRequestAgentService requestService;

    // Endpoint pour récupérer les demandes "PENDING"
    @GetMapping("/pending")
    public List<RegistrationRequestAgent> getPendingRequests() {
        return requestService.getPendingRequests();
    }

    // Endpoint pour ajouter une nouvelle demande
    @PostMapping
    public ResponseEntity<RegistrationRequestAgent> addRequest(@RequestBody RegistrationRequestAgent request) {
        RegistrationRequestAgent newRequest = requestService.addRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRequest);
    }
    

    // Endpoint pour reject une demande
    @PutMapping("/{id}/reject")
    public ResponseEntity<RegistrationRequestAgent> rejectRequest(@PathVariable Long id) {
        RegistrationRequestAgent updatedRequest = requestService.rejectRequest(id);
        return ResponseEntity.ok(updatedRequest);
    }
}