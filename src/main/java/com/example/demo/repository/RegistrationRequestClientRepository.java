package com.example.demo.repository;



    import com.example.demo.models.RegistrationRequestClient;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;
    import com.example.demo.models.RegistrationStatus;
    import java.util.List;

    @Repository
    public interface RegistrationRequestClientRepository extends JpaRepository<RegistrationRequestClient, Long> {
        List<RegistrationRequestClient> findByStatus(RegistrationStatus status);
    }
    
