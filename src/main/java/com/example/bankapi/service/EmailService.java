package com.example.bankapi.service;

import com.example.bankapi.dao.EmailRepository;
import com.example.bankapi.dto.EmailAddDto;
import com.example.bankapi.dto.UiSuccessContainer;
import com.example.bankapi.exeption.LimitException;
import com.example.bankapi.exeption.NotFoundException;
import com.example.bankapi.exeption.UserAlreadyExistsException;
import com.example.bankapi.models.Email;
import com.example.bankapi.models.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository repository;
    private final UserService userService;

    public void checkEmail(String address) {
        if (repository.existsByAddress(address)) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже существует");
        }
    }

    public Email create(Email email){
        return repository.save(email);
    }

    public EmailAddDto addEmail(String address) {
        UserEntity userEntity = userService.getCurrentUser();
        checkEmail(address);
        Email email = new Email();
        email.setUser(userEntity);
        email.setAddress(address);
        create(email);
        return new EmailAddDto(userEntity.getLogin(), address);
    }

    public UiSuccessContainer deleteEmail(String address) {
        UserEntity userEntity = userService.getCurrentUser();
        List<Email> emails = userEntity.getEmails();
        if(emails.size() <= 1){
            throw new LimitException("У пользователя нет прав его удалить последний email");
        }
        Email email = repository.findByAddressAndUserId(address, userEntity.getId()).orElseThrow(
                () -> new NotFoundException("Email не найден или у пользователя нет прав его удалить")
        );

        repository.delete(email);
        return new UiSuccessContainer(true, "Удален email: " + address);
    }

    public UserEntity getUserByEmail(String address) {
        Email email = repository.findByAddress(address).orElseThrow(
                () -> new NotFoundException("Email не найден")
        );
        return email.getUser();
    }
}
