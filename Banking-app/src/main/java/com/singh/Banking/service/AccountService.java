package com.singh.Banking.service;

import com.singh.Banking.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getaccountById(Long Id);

    AccountDto deposite(Long Id, double  amount);

    AccountDto withdraw(Long Id, double amount);

    List<AccountDto> getAllAccounts();

    AccountDto deleteAccount(Long Id);
}
