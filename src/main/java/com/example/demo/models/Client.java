package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Clients")
@PrimaryKeyJoinColumn(name = "id")
@Inheritance(strategy = InheritanceType.JOINED)
public class Client extends User {

    @Column(nullable = false)
    private String clientType; // Possible values: 'HSSAB1', 'HSSAB2', 'HSSAB3'

    @Column(nullable = false)
    private String idType; // Possible values: 'CIN', 'Passport', 'Residence permit'

    @Column(nullable = false, unique = true)
    private String idNumber;

    @Column(nullable = false)
    private double balance;

    // Constructeurs
    public Client() {
        super();
    }

    public Client(String clientType, String idType, String idNumber, double balance) {
        super();
        this.clientType = clientType;
        this.idType = idType;
        this.idNumber = idNumber;
        this.balance = balance;
    }

    // Getters et Setters
    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
