package com.example.demo.services;

import com.example.demo.models.Client;
import com.example.demo.models.User;
import com.example.demo.models.PasswordGenerator;
import com.example.demo.models.RegistrationRequestClient;
import com.example.demo.models.RegistrationStatus;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.RegistrationRequestClientRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationRequestClientService {

    @Autowired
    private RegistrationRequestClientRepository requestRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    // Récupérer toutes les requêtes en attente
    public List<RegistrationRequestClient> getPendingRequests() {
        return requestRepository.findByStatus(RegistrationStatus.PENDING);
    }

    // Ajouter une nouvelle requête
    public RegistrationRequestClient addRequest(RegistrationRequestClient request) {
        return requestRepository.save(request);
    }

    // Accepter une requête (insertion du client, mise à jour de l'état et ajout d'un utilisateur)
    public void acceptRequest(Long requestId) {
        Optional<RegistrationRequestClient> requestOptional = requestRepository.findById(requestId);
        if (requestOptional.isPresent()) {
            RegistrationRequestClient request = requestOptional.get();
            // Générer un mot de passe sécurisé pour l'utilisateur
            PasswordGenerator passwordGenerator = new PasswordGenerator();
            String generatedPassword = passwordGenerator.generatePassword();
            Client client = new Client(
                    request.getClientType(),
                    request.getIdType(),
                    request.getIdNumber(),
                    0.0 // solde initial
            );
            client.setFirstName(request.getFirstName());
            client.setLastName(request.getLastName());
            client.setPhone(request.getPhone());
            client.setEmail(request.getEmail());

            // Sauvegarder le client
            clientRepository.save(client);

            // Créer un utilisateur avec les informations de la requête et un mot de passe généré
                    
                    User user = new User(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPhone(),
                        generatedPassword // Le mot de passe généré
                );

                // Sauvegarder l'utilisateur dans la base de données
                userRepository.save(user);

            // Mettre à jour le statut de la demande
            request.setStatus(RegistrationStatus.ACCEPTED);
            requestRepository.save(request);
        }
    }

    // Refuser une requête (mise à jour de l'état)
    public void refuseRequest(Long requestId) {
        Optional<RegistrationRequestClient> requestOptional = requestRepository.findById(requestId);
        if (requestOptional.isPresent()) {
            RegistrationRequestClient request = requestOptional.get();
            request.setStatus(RegistrationStatus.REJECTED);
            requestRepository.save(request);
        }
    }

    
}
