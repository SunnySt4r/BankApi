package com.example.bankapi.dao;

import com.example.bankapi.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long>{
    boolean existsByAddress(String address);
    Optional<Email> findByAddressAndUserId(String address, Long userId);

    Optional<Email> findByAddress(String address);
}
