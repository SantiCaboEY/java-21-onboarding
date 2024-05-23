package com.example.mspersonas.controller;

import com.example.mspersonas.dto.CreatePersonDto;
import com.example.mspersonas.dto.CreatePersonResponseDto;
import com.example.mspersonas.dto.GetPersonDto;
import com.example.mspersonas.service.PersonService;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/person")
@Log
public class PersonController {

    private final PersonService personService;

    public PersonController(final PersonService personService){
        this.personService = personService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public CreatePersonResponseDto addPerson(@RequestBody @Valid CreatePersonDto createPersonDto){
        return personService.addPerson(createPersonDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetPersonDto getPerson(@PathVariable String id){
        return personService.getPerson(id);
    }

    //Todo Interceptor log requestResponse
    //Todo: Dar de baja cliente
}
