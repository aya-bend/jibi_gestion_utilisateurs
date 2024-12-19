package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.RegistrationRequestAgent;
import com.example.demo.models.RegistrationStatus;

public interface RegistrationRequestAgentRepository extends JpaRepository<RegistrationRequestAgent, Long>{
    List<RegistrationRequestAgent> findByStatus(RegistrationStatus status);
}
