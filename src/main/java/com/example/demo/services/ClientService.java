package com.example.demo.services;

import com.example.demo.models.Client;
import com.example.demo.models.ClientDeleted;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ClientDeletedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientDeletedRepository clientDeletedRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientDeletedRepository clientDeletedRepository) {
        this.clientRepository = clientRepository;
        this.clientDeletedRepository = clientDeletedRepository;
    }

    // Récupérer tous les clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Mettre à jour un client
    public Client updateClient(Long id, Client updatedClient) {
        Optional<Client> clientOpt = clientRepository.findById(id);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            client.setFirstName(updatedClient.getFirstName());
            client.setLastName(updatedClient.getLastName());
            client.setPhone(updatedClient.getPhone());
            client.setEmail(updatedClient.getEmail());
            client.setClientType(updatedClient.getClientType());
            client.setIdType(updatedClient.getIdType());
            client.setIdNumber(updatedClient.getIdNumber());
            client.setBalance(updatedClient.getBalance());
            return clientRepository.save(client);
        }
        return null;
    }

    // Supprimer un client (ajouter à ClientsDeleted)
    public boolean deleteClient(Long id, String motif, String description) {
        Optional<Client> clientOpt = clientRepository.findById(id);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            
            // Créer un nouvel objet ClientDeleted avec les informations de suppression
            ClientDeleted clientDeleted = new ClientDeleted(motif, description);
            clientDeleted.setId(client.getId());
            clientDeleted.setFirstName(client.getFirstName());
            clientDeleted.setLastName(client.getLastName());
            clientDeleted.setPhone(client.getPhone());
            clientDeleted.setEmail(client.getEmail());
            clientDeleted.setClientType(client.getClientType());
            clientDeleted.setIdType(client.getIdType());
            clientDeleted.setIdNumber(client.getIdNumber());
            clientDeleted.setBalance(client.getBalance());

            // Enregistrer le client supprimé dans la table ClientsDeleted
            clientDeletedRepository.save(clientDeleted);

            // Supprimer le client de la table Clients
            clientRepository.deleteById(id);

            return true;
        }
        return false;
    }
}

