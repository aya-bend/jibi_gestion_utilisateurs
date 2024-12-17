package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.RegistrationRequest;
import com.example.demo.models.RegistrationStatus;
import com.example.demo.repository.RegistrationRequestRepository;

@Service
public class RegistrationRequestService {

    @Autowired
    private RegistrationRequestRepository requestRepository;

    // Méthode pour récupérer les demandes avec statut PENDING
    public List<RegistrationRequest> getPendingRequests() {
        return requestRepository.findByStatus(RegistrationStatus.PENDING);
    }

    // Méthode pour ajouter une nouvelle demande
    public RegistrationRequest addRequest(RegistrationRequest request) {
        // Le statut est déjà initialisé par défaut à "PENDING" dans l'entité
        return requestRepository.save(request);
    }
}
