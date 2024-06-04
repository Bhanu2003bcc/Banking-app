package com.singh.Banking.controller;

import com.singh.Banking.dto.AccountDto;
import com.singh.Banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")

public class AccountController {
    private AccountService accountService;
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    //Add Account Rest Api
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    // Get account REST API

    @GetMapping("/{Id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long Id){
        AccountDto accountDto = accountService.getaccountById(Id);
        return ResponseEntity.ok(accountDto);
    }

    // deposite REST API

    @PutMapping("/{Id}/deposit")
    public ResponseEntity<AccountDto> deposite(@PathVariable Long Id,
                                               @RequestBody Map<String, Double> request){

        Double amount = request.get("amount");
        AccountDto accountDto= accountService.deposite(Id, amount);
        return ResponseEntity.ok(accountDto);
    }

    // withdraw REST API
    @PutMapping("/{Id}/withdraw")
    public  ResponseEntity<AccountDto> wihtdraw(@PathVariable Long Id,Map<String, Double> request ){
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(Id, amount);
        return ResponseEntity.ok(accountDto);
    }

    //Get All Account REST API
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/{Id}/delete")
    public ResponseEntity<String> deleteAccount (@PathVariable Long Id){
        accountService.deleteAccount(Id);
        return ResponseEntity.ok("Account is deleted Successfully! ");
    }
}
