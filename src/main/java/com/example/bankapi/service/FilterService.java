package com.example.bankapi.service;

import com.example.bankapi.models.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterService {

    private final EmailService emailService;
    private final PhoneService phoneService;
    private final UserService userService;

    public Page<UserEntity> getUsers(Date birthday, PageRequest pageRequest) {
        List<UserEntity> userEntities = userService.getUsersByBirthday(birthday);
        return new PageImpl<>(userEntities, pageRequest, userEntities.size());
    }

    public UserEntity getUsers(String email) {
        return emailService.getUserByEmail(email);
    }

    public UserEntity getUsers(Long phone) {
        return phoneService.getUserByPhone(phone);
    }

    public Page<UserEntity> getUsers(String name, String surname, String middleName, PageRequest pageRequest) {
        List<UserEntity> userEntities = userService.getUsersByName(name, surname, middleName);
        return new PageImpl<>(userEntities, pageRequest, userEntities.size());
    }
}
