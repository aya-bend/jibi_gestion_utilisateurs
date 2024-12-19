package com.example.demo.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ClientsDeleted")
@PrimaryKeyJoinColumn(name = "id")
public class ClientDeleted extends Client {

    @Column(nullable = false)
    private String motif; // Motif de suppression

    @Column(length = 500)
    private String description; // Description facultative

    @Column(name = "deleted_at", nullable = false)
    private LocalDateTime deletedAt; // Date et heure de suppression

    // Constructeurs
    public ClientDeleted() {
        super();
        // Par défaut, la date de suppression est la date actuelle
        this.deletedAt = LocalDateTime.now();
    }

    public ClientDeleted(String motif, String description) {
        super();
        this.motif = motif;
        this.description = description;
        this.deletedAt = LocalDateTime.now();
    }

    // Getters et Setters
    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}

