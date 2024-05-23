package com.example.bankapi.service;

import com.example.bankapi.dao.AccountRepository;
import com.example.bankapi.dto.AccountResponseDto;
import com.example.bankapi.dto.TransactionDto;
import com.example.bankapi.exeption.LimitException;
import com.example.bankapi.models.Account;
import com.example.bankapi.models.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class AccountService {

    private final AccountRepository repository;
    private final UserService userService;

    public Account create(Account account){
        return repository.save(account);
    }


    @Scheduled(fixedRate = 60000)
    @Transactional
    public void sendPercents(){
        List<Account> accountList = repository.findAll();
        accountList.stream().filter(
            account -> account.getMoney() != null
        ).forEach(
            this::addPercents
        );
        repository.saveAll(accountList);
    }


    private void addPercents(Account account){
        Float deposit = account.getDeposit();
        Float money = account.getMoney();
        if(money / deposit > 2.07){
            return;
        }
        money = Math.min(money + money * 0.05f, deposit * 2.07f);

        account.setMoney(money);
    }


    public AccountResponseDto getBalance() {
        UserEntity userEntity = userService.getCurrentUser();
        Account account = userEntity.getAccount();
        return new AccountResponseDto(account.getMoney());
    }


    @Transactional
    public TransactionDto sendMoney(String login, Float money) {
        UserEntity toUserEntity = userService.getByLogin(login);
        UserEntity fromUserEntity = userService.getCurrentUser();
        Account fromAccount = fromUserEntity.getAccount();
        Account toAccount = toUserEntity.getAccount();
        if(fromAccount.getMoney() < money){
            throw new LimitException("Недостаточно средств");
        }
        fromAccount.setMoney(fromAccount.getMoney() - money);
        toAccount.setMoney(toAccount.getMoney() + money);
        repository.save(fromAccount);
        repository.save(toAccount);
        return new TransactionDto(fromUserEntity.getLogin(), money, toUserEntity.getLogin());
    }
}
