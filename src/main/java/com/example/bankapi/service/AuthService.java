package com.example.bankapi.service;

import com.example.bankapi.models.Account;
import com.example.bankapi.models.Email;
import com.example.bankapi.models.Phone;
import com.example.bankapi.models.UserEntity;
import com.example.bankapi.dto.JwtAuthenticationResponse;
import com.example.bankapi.dto.user.UserSingInDto;
import com.example.bankapi.dto.user.UserSingUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AccountService accountService;
    private final PhoneService phoneService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public JwtAuthenticationResponse signUp(UserSingUpDto request) {
        phoneService.checkPhone(request.getPhone());
        emailService.checkEmail(request.getEmail());
        userService.checkLogin(request.getLogin());

        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(request.getLogin());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity = userService.create(userEntity);

        Phone phone = new Phone();
        phone.setNumber(request.getPhone());
        phone.setUser(userEntity);
        phoneService.create(phone);

        Email email = new Email();
        email.setAddress(request.getEmail());
        email.setUser(userEntity);
        emailService.create(email);

        Account account = new Account();
        account.setDeposit(request.getDeposit());
        account.setMoney(request.getDeposit());
        account.setUser(userEntity);
        accountService.create(account);

        var jwt = jwtService.generateToken(userEntity);
        return new JwtAuthenticationResponse(jwt);
    }


    public JwtAuthenticationResponse signIn(UserSingInDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getLogin());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}