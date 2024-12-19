package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.RegistrationRequestAgent;
import com.example.demo.models.RegistrationStatus;
import com.example.demo.repository.RegistrationRequestAgentRepository;

@Service
public class RegistrationRequestAgentService {

    @Autowired
    private RegistrationRequestAgentRepository requestRepository;

    // Méthode pour récupérer les demandes avec statut PENDING
    public List<RegistrationRequestAgent> getPendingRequests() {
        return requestRepository.findByStatus(RegistrationStatus.PENDING);
    }

    // Méthode pour ajouter une nouvelle demande
    public RegistrationRequestAgent addRequest(RegistrationRequestAgent request) {
        // Le statut est déjà initialisé par défaut à "PENDING" dans l'entité
        return requestRepository.save(request);
    }
}
