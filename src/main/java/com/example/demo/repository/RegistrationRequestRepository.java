package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.RegistrationRequest;
import com.example.demo.models.RegistrationStatus;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long>{
    List<RegistrationRequest> findByStatus(RegistrationStatus status);
}
