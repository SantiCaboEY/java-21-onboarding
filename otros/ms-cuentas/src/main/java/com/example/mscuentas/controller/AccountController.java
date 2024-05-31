package com.example.mscuentas.controller;

import com.example.mscuentas.dto.GetAccountDto;
import com.example.mscuentas.service.AccountService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/account")
@Log
public class AccountController {

    private final AccountService accountService;

    public AccountController(final AccountService accountService){
        this.accountService = accountService;
    }
/*
    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public CreateAccountResponseDto addPerson(@RequestBody @Valid CreateAccountDto createAccountDto){
        return accountService.addPerson(createAccountDto);
    }*/

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetAccountDto getAccount(@PathVariable String id){
        return accountService.getAccount(id);
    }
}
