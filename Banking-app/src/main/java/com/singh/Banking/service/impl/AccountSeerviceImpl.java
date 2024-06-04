package com.singh.Banking.service.impl;

import com.singh.Banking.dto.AccountDto;
import com.singh.Banking.entity.Account;
import com.singh.Banking.mapper.AccountMapper;
import com.singh.Banking.reposetiory.AccountRepository;
import com.singh.Banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountSeerviceImpl implements AccountService {
    private AccountRepository accountRepository;


    public AccountSeerviceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.maptoAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public AccountDto getaccountById(Long Id) {
        Account account = accountRepository.findById(Id).orElseThrow(() -> new RuntimeException("Account does not exists"));
        return AccountMapper.maptoAccountDto(account);
    }

    @Override
    public AccountDto deposite(Long Id, double amount) {
        Account account = accountRepository.findById(Id).orElseThrow(() -> new RuntimeException("Account does not exists"));
        double total =  account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long Id, double amount) {
        Account account = accountRepository.findById(Id).orElseThrow(() -> new RuntimeException("Account does not exists"));
        if(account.getBalance() < amount){
         throw new RuntimeException("Insufficient Balance");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.maptoAccountDto(savedAccount);

    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return  accounts.stream().map((account) -> AccountMapper.maptoAccountDto(account)).collect(Collectors.toList());

    }

    @Override
    public AccountDto deleteAccount(Long Id) {
        Account account = accountRepository.findById(Id).orElseThrow(() -> new RuntimeException("Account does not exists"));
        accountRepository.deleteById(Id);
        return null;
    }
}
