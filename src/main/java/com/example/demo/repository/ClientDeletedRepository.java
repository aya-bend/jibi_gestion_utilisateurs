package com.example.demo.repository;



import com.example.demo.models.ClientDeleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDeletedRepository extends JpaRepository<ClientDeleted, Long> {
   
}

