package com.example.bankapi.dao;

import com.example.bankapi.models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>{
    boolean existsByNumber(Long phone);

    Optional<Phone> findByNumberAndUserId(Long number, Long userId);

    Optional<Phone> findByNumber(Long number);
}
