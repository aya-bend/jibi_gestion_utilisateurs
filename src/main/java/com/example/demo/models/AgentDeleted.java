package com.example.demo.models;


import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "AgentsDeleted")
@PrimaryKeyJoinColumn(name = "id")
public class AgentDeleted extends Agent {

    @Column(nullable = false)
    private String motif;

    @Column(length = 500)
    private String description;

    @Column(name = "deleted_at", nullable = false)
    private LocalDateTime deletedAt;


    public AgentDeleted() {
        // Par défaut, la date de suppression est la date actuelle
        this.deletedAt = LocalDateTime.now();
    }
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
