package com.example.bankapi.service;

import com.example.bankapi.dao.UserRepository;
import com.example.bankapi.exeption.NotFoundException;
import com.example.bankapi.exeption.UserAlreadyExistsException;
import com.example.bankapi.models.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserEntity save(UserEntity userEntity) {
        return repository.save(userEntity);
    }


    public UserEntity create(UserEntity userEntity) {
        return save(userEntity);
    }


    public UserEntity getByLogin(String login) {
        return repository.findByLogin(login)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));

    }


    public UserDetailsService userDetailsService() {
        return this::getByLogin;
    }


    public UserEntity getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return getByLogin(authentication.getName());
    }

    public void checkLogin(String login) {
        if (repository.existsByLogin(login)) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
        }
    }

    public String setName(String surname, String name, String middleName) {
        UserEntity userEntity = getCurrentUser();
        if(userEntity.getSurname().isEmpty()){
            userEntity.setSurname(surname);
        }
        if(userEntity.getName().isEmpty()){
            userEntity.setName(name);
        }
        if(userEntity.getMiddleName().isEmpty()){
            userEntity.setMiddleName(middleName);
        }
        userEntity = repository.save(userEntity);
        return userEntity.getSurname() + userEntity.getName() + userEntity.getMiddleName();
    }

    public Date setBirthday(Date birthday) {
        UserEntity userEntity = getCurrentUser();
        if(userEntity.getBirthday() == null){
            userEntity.setBirthday(birthday);
        }
        userEntity = repository.save(userEntity);
        return userEntity.getBirthday();
    }

    public List<UserEntity> getUsersByBirthday(Date birthday) {
        return repository.findAllByBirthdayIsGreaterThanEqual(birthday);
    }

    public List<UserEntity> getUsersByName(String name, String surname, String middleName) {
        return repository.findAllByNameLikeIgnoreCaseOrSurnameLikeIgnoreCaseOrMiddleNameLikeIgnoreCase(name, surname, middleName);
    }
}
