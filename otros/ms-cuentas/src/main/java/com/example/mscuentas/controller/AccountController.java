package com.example.mscuentas.controller;

import com.example.mscuentas.dto.CreateAccountDto;
import com.example.mscuentas.dto.CreateAccountResponseDto;
import com.example.mscuentas.dto.GetAccountDto;
import com.example.mscuentas.service.AccountService;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/person")
@Log
public class AccountController {

    private final AccountService accountService;

    public AccountController(final AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public CreateAccountResponseDto addPerson(@RequestBody @Valid CreateAccountDto createAccountDto){
        return accountService.addPerson(createAccountDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetAccountDto getPerson(@PathVariable String id){
        return accountService.getPerson(id);
    }

    //Todo Interceptor log requestResponse
    //Todo: Dar de baja cliente
}
