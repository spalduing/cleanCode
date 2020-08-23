package com.spalduing.cache.springredisbetroulet.controllers;

import com.spalduing.cache.springredisbetroulet.classes.Bet;
import com.spalduing.cache.springredisbetroulet.repositories.BetRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rest/bet")
public class BetController {
    final private BetRepository betRepository;

    public BetController(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    @PostMapping("/addB/{id}")
    public Bet addBet(@PathVariable("id") final String id, @RequestBody Bet bet){
        bet.setUserId(id);
        betRepository.save(bet);

        return betRepository.findById(bet);
    }

    @GetMapping("/findB/{id}")
    public Bet findBet(@PathVariable("id") final String id){
        Bet tempBet = new Bet(id, "", "", "", 0, 0.0);

        return betRepository.findById(tempBet);
    }

    @GetMapping("/allB")
    public Map<String, Bet> allBets(){

        return betRepository.findAll();
    }
}
