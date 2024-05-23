package com.example.bankapi.dao;

import com.example.bankapi.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, QuerydslPredicateExecutor<UserEntity> {
    boolean existsByLogin(String login);
    Optional<UserEntity> findByLogin(String login);
    List<UserEntity> findAllByBirthdayIsGreaterThanEqual(Date date);
    List<UserEntity> findAllByNameLikeIgnoreCaseOrSurnameLikeIgnoreCaseOrMiddleNameLikeIgnoreCase(String name, String surname, String middleName);
}
