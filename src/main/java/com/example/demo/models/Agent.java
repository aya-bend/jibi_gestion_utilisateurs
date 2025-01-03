package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Agents")
@Inheritance(strategy = InheritanceType.JOINED)

public class Agent extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idType;

    private String idNumber;

    @Lob
    private byte[] idDocument;

    private String birthDate;

    private String address;

    private String immatriculation;

    private String patenteNumber;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
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
