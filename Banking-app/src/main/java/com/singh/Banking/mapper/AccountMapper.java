package com.singh.Banking.mapper;

import com.singh.Banking.dto.AccountDto;
import com.singh.Banking.entity.Account;

// This class is made to MAP Dto into entity

public class AccountMapper {
    public static Account maptoAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }

    public static AccountDto maptoAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }
}
