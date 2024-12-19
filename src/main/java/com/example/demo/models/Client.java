package com.example.demo.models;
import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class Client extends User {

   
    private String clientType; // Possible values: 'HSSAB1', 'HSSAB2', 'HSSAB3'
    private String idType; // Possible values: 'CIN', 'Passport', 'Residence permit'
    private String idNumber;
    private double balance;

    public Client() {
    }

    public Client(String clientType, String idType, String idNumber, double balance) {
        this.clientType = clientType;
        this.idType = idType;
        this.idNumber = idNumber;
        this.balance = balance;
    }

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
