package com.example.bankapi.service;

import com.example.bankapi.dao.PhoneRepository;
import com.example.bankapi.dto.PhoneAddDto;
import com.example.bankapi.dto.UiSuccessContainer;
import com.example.bankapi.exeption.LimitException;
import com.example.bankapi.exeption.NotFoundException;
import com.example.bankapi.exeption.UserAlreadyExistsException;
import com.example.bankapi.models.Phone;
import com.example.bankapi.models.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final PhoneRepository repository;
    private final UserService userService;

    public void checkPhone(Long phone) {
        if (repository.existsByNumber(phone)) {
            throw new UserAlreadyExistsException("Пользователь с таким телефоном уже существует");
        }
    }

    public Phone create(Phone phone){
        return repository.save(phone);
    }

    public PhoneAddDto addPhone(Long number) {
        UserEntity userEntity = userService.getCurrentUser();
        checkPhone(number);
        Phone phone = new Phone();
        phone.setUser(userEntity);
        phone.setNumber(number);
        create(phone);
        return new PhoneAddDto(userEntity.getLogin(), number);
    }

    public UiSuccessContainer deletePhone(Long number) {
        UserEntity userEntity = userService.getCurrentUser();
        List<Phone> phones = userEntity.getPhones();
        if(phones.size() <= 1){
            throw new LimitException("У пользователя нет прав его удалить последний телефон");
        }
        Phone phone = repository.findByNumberAndUserId(number, userEntity.getId()).orElseThrow(
                () -> new NotFoundException("Телефон не найден или у пользователя нет прав его удалить")
        );

        repository.delete(phone);
        return new UiSuccessContainer(true, "Удален номер: " + number);
    }

    public UserEntity getUserByPhone(Long number) {
        Phone phone = repository.findByNumber(number).orElseThrow(
                () -> new NotFoundException("Телефон не найден")
        );
        return phone.getUser();
    }
}
