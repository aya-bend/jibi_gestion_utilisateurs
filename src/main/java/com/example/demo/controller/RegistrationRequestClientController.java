package com.example.demo.controller;

import com.example.demo.models.RegistrationRequestClient;
import com.example.demo.services.RegistrationRequestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registration-requests")
public class RegistrationRequestClientController {

    private final RegistrationRequestClientService registrationRequestClientService;

    @Autowired
    public RegistrationRequestClientController(RegistrationRequestClientService registrationRequestClientService) {
        this.registrationRequestClientService = registrationRequestClientService;
    }

    // Récupérer toutes les requêtes en attente
    @GetMapping("/pending")
    public ResponseEntity<List<RegistrationRequestClient>> getPendingRequests() {
        List<RegistrationRequestClient> pendingRequests = registrationRequestClientService.getPendingRequests();
        return new ResponseEntity<>(pendingRequests, HttpStatus.OK);
    }

    // Ajouter une nouvelle requête
    @PostMapping
    public ResponseEntity<RegistrationRequestClient> addRequest(@RequestBody RegistrationRequestClient request) {
        RegistrationRequestClient savedRequest = registrationRequestClientService.addRequest(request);
        return new ResponseEntity<>(savedRequest, HttpStatus.CREATED);
    }

    // Accepter une requête (insertion du client et ajout de l'utilisateur)
    @PutMapping("/{id}/accept")
    public ResponseEntity<Void> acceptRequest(@PathVariable Long id) {
        registrationRequestClientService.acceptRequest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Refuser une requête
    @PutMapping("/{id}/refuse")
    public ResponseEntity<Void> refuseRequest(@PathVariable Long id) {
        registrationRequestClientService.refuseRequest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

