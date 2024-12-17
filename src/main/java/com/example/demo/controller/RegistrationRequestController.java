package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.RegistrationRequest;
import com.example.demo.services.RegistrationRequestService;


@RestController
@RequestMapping("/api/backoffice/registration-requests")
public class RegistrationRequestController {

    @Autowired
    private RegistrationRequestService requestService;

    // Endpoint pour récupérer les demandes "PENDING"
    @GetMapping("/pending")
    public List<RegistrationRequest> getPendingRequests() {
        return requestService.getPendingRequests();
    }

    // Endpoint pour ajouter une nouvelle demande
    @PostMapping
    public RegistrationRequest addRequest(@RequestBody RegistrationRequest request) {
        return requestService.addRequest(request);
    }
}