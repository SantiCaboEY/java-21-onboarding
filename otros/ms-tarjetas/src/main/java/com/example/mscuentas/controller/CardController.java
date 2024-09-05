package com.example.mscuentas.controller;

import com.example.mscuentas.dto.GetCardDto;
import com.example.mscuentas.service.CardService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/card")
@Log
public class CardController {

    private final CardService cardService;

    public CardController(final CardService cardService){
        this.cardService = cardService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCardDto getCard(@PathVariable String id){
        return cardService.getCard(id);
    }
}
