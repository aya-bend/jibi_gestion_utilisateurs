package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.RegistrationRequestAgent;
import com.example.demo.services.RegistrationRequestAgentService;


@RestController
@RequestMapping("/api/backoffice/registration-requests")
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
    public RegistrationRequestAgent addRequest(@RequestBody RegistrationRequestAgent request) {
        return requestService.addRequest(request);
    }
}