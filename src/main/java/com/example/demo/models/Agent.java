package com.example.demo.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Agents")
@Inheritance(strategy = InheritanceType.JOINED)// Spécifie que Agent est une entité héritée
public class Agent extends User {


    @Column(nullable = false)
    private String idType;

    @Column(nullable = false, unique = true)
    private String idNumber;

    @Lob
    @Column(name = "id_document", columnDefinition = "BLOB")
    private byte[] idDocument;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String immatriculation;

    @Column(nullable = false)
    private String patenteNumber;


    // Constructeurs
    public Agent() {
        super();
    }

    public Agent(String idType, String idNumber, byte[] idDocument, LocalDate birthDate, String address, String immatriculation, String patenteNumber) {
        super();
        this.idType = idType;
        this.idNumber = idNumber;
        this.idDocument = idDocument;
        this.birthDate = birthDate;
        this.address = address;
        this.immatriculation = immatriculation;
        this.patenteNumber = patenteNumber;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getPatenteNumber() {
        return patenteNumber;
    }

    public void setPatenteNumber(String patenteNumber) {
        this.patenteNumber = patenteNumber;
    }
    public byte[] getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(byte[] idDocument) {
        this.idDocument = idDocument;
    }
}
