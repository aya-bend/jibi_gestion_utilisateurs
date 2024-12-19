package com.example.demo.models;


import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin extends User {

    @Column(nullable = false)
    private String adminLevel;

    @Column(nullable = false)
    private String department;

    // Constructeur par défaut
    public Admin() {
        super();
    }

    // Constructeur avec données
    public Admin(String adminLevel, String department) {
        super();
        this.adminLevel = adminLevel;
        this.department = department;
    }
    // Getters et Setters
    public String getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
