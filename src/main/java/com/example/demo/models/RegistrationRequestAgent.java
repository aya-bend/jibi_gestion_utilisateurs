package com.example.demo.models;

import jakarta.persistence.*;


@Entity
@Table(name = "registration_requests")
public class RegistrationRequestAgent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String idType;
    private String idNumber;
    @Lob
    private byte[] idDocument;
    private String email;
    private String phone;
    private String immatriculation;
    private String patentNumber;
    @Enumerated(EnumType.STRING) // Pour stocker l'Enum en format texte dans la DB
    private RegistrationStatus status = RegistrationStatus.PENDING; // Par défaut "PENDING"




    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getIdType() { return idType; }
    public void setIdType(String idType) { this.idType = idType; }

    public String getIdNumber() { return idNumber; }
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }

    public byte[] getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(byte[] idDocument) {
        this.idDocument = idDocument;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getImmatriculation() { return immatriculation; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }

    public String getPatentNumber() { return patentNumber; }
    public void setPatentNumber(String patentNumber) { this.patentNumber = patentNumber; }

    public RegistrationStatus getStatus() { return status; }
    public void setStatus(RegistrationStatus status) { this.status = status; }
}
