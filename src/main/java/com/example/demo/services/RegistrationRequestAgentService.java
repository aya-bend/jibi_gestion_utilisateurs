package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Agent;
import com.example.demo.models.RegistrationRequestAgent;
import com.example.demo.models.RegistrationStatus;
import com.example.demo.repository.AgentRepository;
import com.example.demo.repository.RegistrationRequestAgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.LocalDate;

@Service
public class RegistrationRequestAgentService {

    @Autowired
    private RegistrationRequestAgentRepository requestRepository;

    @Autowired
    private AgentRepository agentRepository; // Repository pour insérer un Agent dans la base de données

    private final String AUTH_SERVICE_URL = "http://auth-microservice/api/users";

    // Méthode pour récupérer les demandes avec statut PENDING
    public List<RegistrationRequestAgent> getPendingRequests() {
        return requestRepository.findByStatus(RegistrationStatus.PENDING);
    }

    // Méthode pour ajouter une nouvelle demande
    public RegistrationRequestAgent addRequest(RegistrationRequestAgent request) {
        return requestRepository.save(request);
    }

    // Rejet de la demande
    public void rejectRequest(Long id) {
        RegistrationRequestAgent request = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(RegistrationStatus.REJECTED);
        requestRepository.save(request);
    }

    // Acceptation de la demande
    public void acceptRequest(Long id) {
        // Récupérer la demande d'inscription par ID
        RegistrationRequestAgent request = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        // Créer un nouvel Agent à partir des données de la demande
        Agent agent = new Agent(
                request.getIdType(),
                request.getIdNumber(),
                request.getIdDocument(),
                request.getBirthDate(),
                request.getAddress(),
                request.getImmatriculation(),
                request.getPatentNumber()
        );
        agent.setFirstName(request.getFirstName());
        agent.setLastName(request.getLastName());
        agent.setEmail(request.getEmail());
        agent.setPhone(request.getPhone());
        agent.setPassword(generateRandomPassword()); // Générer un mot de passe pour l'agent

        // Sauvegarder l'agent dans la table des agents
        agentRepository.save(agent);

        // Envoyer les informations au microservice d'authentification
        RestTemplate restTemplate = new RestTemplate();
        String password = agent.getPassword(); // Utiliser le mot de passe généré
        String role = "agent";

        // Construire le payload
        UserPayload payload = new UserPayload(agent.getPhone(), password, role);

        // Envoyer une requête POST au microservice
        restTemplate.postForObject(AUTH_SERVICE_URL, payload, String.class);

        // Mettre à jour le statut de la demande à ACCEPTED
        request.setStatus(RegistrationStatus.ACCEPTED);
        requestRepository.save(request);
    }

    // Méthode pour générer un mot de passe aléatoire
    private String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        byte[] passwordBytes = new byte[8];
        random.nextBytes(passwordBytes);
        return new String(passwordBytes, StandardCharsets.UTF_8)
                .replaceAll("[^a-zA-Z0-9]", ""); // Filtrer les caractères spéciaux
    }

    // Classe interne pour construire le payload à envoyer au microservice
    private static class UserPayload {
        private String phone;
        private String password;
        private String role;

        public UserPayload(String phone, String password, String role) {
            this.phone = phone;
            this.password = password;
            this.role = role;
        }

        // Getters et Setters
        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
