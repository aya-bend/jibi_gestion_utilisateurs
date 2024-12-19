package com.example.demo.services;


import com.example.demo.models.Agent;
import com.example.demo.models.AgentDeleted;
import com.example.demo.repository.AgentDeletedRepository;
import com.example.demo.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private AgentDeletedRepository agentDeletedRepository;

    /**
     * Récupère la liste de tous les agents.
     */
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    /**
     * Récupère un agent par son ID.
     */
    public Optional<Agent> getAgentById(Long id) {
        return agentRepository.findById(id);
    }

    /**
     * Enregistre un nouvel agent.
     */
    public Agent saveAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    /**
     * Met à jour les informations d'un agent existant.
     */
    public Agent updateAgent(Long id, Agent updatedAgent) {
        return agentRepository.findById(id).map(agent -> {
            agent.setFirstName(updatedAgent.getFirstName());
            agent.setLastName(updatedAgent.getLastName());
            agent.setEmail(updatedAgent.getEmail());
            agent.setPhone(updatedAgent.getPhone());
            agent.setIdType(updatedAgent.getIdType());
            agent.setIdNumber(updatedAgent.getIdNumber());
            agent.setIdDocument(updatedAgent.getIdDocument());
            agent.setBirthDate(updatedAgent.getBirthDate());
            agent.setAddress(updatedAgent.getAddress());
            agent.setImmatriculation(updatedAgent.getImmatriculation());
            agent.setPatenteNumber(updatedAgent.getPatenteNumber());
            return agentRepository.save(agent);
        }).orElseThrow(() -> new RuntimeException("Agent not found"));
    }

    /**
     * Supprime un agent en enregistrant ses informations dans la table `AgentsDeleted`.
     */
    public void deleteAgent(Long id, String motif, String description) {
        agentRepository.findById(id).ifPresent(agent -> {
            // Création d'un objet AgentDeleted pour conserver les informations supprimées
            AgentDeleted agentDeleted = new AgentDeleted();
            agentDeleted.setFirstName(agent.getFirstName());
            agentDeleted.setLastName(agent.getLastName());
            agentDeleted.setEmail(agent.getEmail());
            agentDeleted.setPhone(agent.getPhone());
            agentDeleted.setPassword(agent.getPassword()); // Hérité de User
            agentDeleted.setIdType(agent.getIdType());
            agentDeleted.setIdNumber(agent.getIdNumber());
            agentDeleted.setIdDocument(agent.getIdDocument());
            agentDeleted.setBirthDate(agent.getBirthDate());
            agentDeleted.setAddress(agent.getAddress());
            agentDeleted.setImmatriculation(agent.getImmatriculation());
            agentDeleted.setPatenteNumber(agent.getPatenteNumber());
            agentDeleted.setMotif(motif);
            agentDeleted.setDescription(description);
            // Sauvegarde de l'agent supprimé dans la table des agents supprimés
            agentDeletedRepository.save(agentDeleted);
            // Suppression de l'agent de la table principale
            agentRepository.delete(agent);
        });
    }

}
