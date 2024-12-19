package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.RegistrationRequestAgent;
import com.example.demo.services.RegistrationRequestAgentService;


@RestController
@RequestMapping("/api/backoffice/registration-requests")
public class RegistrationRequestControllerAgent {

    @Autowired
    private RegistrationRequestAgentService requestService;

    // Endpoint pour récupérer les demandes "PENDING"
    @GetMapping("/pending")
    public List<RegistrationRequestAgent> getPendingRequests() {
        return requestService.getPendingRequests();
    }

    // Endpoint pour ajouter une nouvelle demande
    @PostMapping
    public RegistrationRequestAgent addRequest(@RequestBody RegistrationRequestAgent request) {
        return requestService.addRequest(request);
    }
    @PostMapping("/{id}/reject")
    public ResponseEntity<String> rejectRequest(@PathVariable Long id) {
        requestService.rejectRequest(id);
        return ResponseEntity.ok("Request rejected");
    }

    // Endpoint pour accepter une demande
    @PutMapping("/{id}/accept")
    public ResponseEntity<Void> acceptRequest(@PathVariable Long id) {
        requestService.acceptRequest(id);
        return ResponseEntity.noContent().build();
    }

}