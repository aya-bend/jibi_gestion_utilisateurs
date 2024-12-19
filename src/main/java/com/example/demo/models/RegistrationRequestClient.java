package com.example.demo.models;


import jakarta.persistence.*;


@Entity
@Table(name = "registration_requests_clients")
public class RegistrationRequestClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String clientType; // Enum pour le type de client
    private String idType; // CIN, Passport ou Residence permit
    private String idNumber; // Numéro de l'identité
    private String idDocument; // chemin du fichier pour le document d'identité
    private String incomeProof; // chemin du fichier pour la preuve de revenu
    @Enumerated(EnumType.STRING) // Pour stocker l'Enum en format texte dans la DB
    private RegistrationStatus status = RegistrationStatus.PENDING; // Par défaut "PENDING"

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getClientType() { return clientType; }
    public void setClientType(String clientType) { this.clientType = clientType; }

    public String getIdType() { return idType; }
    public void setIdType(String idType) { this.idType = idType; }

    public String getIdNumber() { return idNumber; }
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }

    public String getIdDocument() { return idDocument; }
    public void setIdDocument(String idDocument) { this.idDocument = idDocument; }

    public String getIncomeProof() { return incomeProof; }
    public void setIncomeProof(String incomeProof) { this.incomeProof = incomeProof; }
}

enum ClientType {
    HSSAB1, HSSAB2, HSSAB3;
}

