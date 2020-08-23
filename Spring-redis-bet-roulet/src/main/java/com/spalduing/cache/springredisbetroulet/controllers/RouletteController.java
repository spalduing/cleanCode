package com.spalduing.cache.springredisbetroulet.controllers;

import com.spalduing.cache.springredisbetroulet.classes.Roulette;
import com.spalduing.cache.springredisbetroulet.repositories.RouletteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rest/roulette")
public class RouletteController {
    final private RouletteRepository rouletteRepository;

    public RouletteController(RouletteRepository rouletteRepository) {
        this.rouletteRepository = rouletteRepository;
    }
    @GetMapping("/allR")
    public Map<String, Roulette> allRoulette(){

        return rouletteRepository.findAll();
    }
    @PostMapping("/addR")
    public String addRoulette(@RequestBody Roulette roulette){
        rouletteRepository.save(roulette);

        return roulette.getId();
    }
    @PatchMapping("/openR/{id}")
    public String openRoulette(@PathVariable("id")  final String id){
        Roulette tempRoulette = new Roulette(id,"","");

        return rouletteRepository.openRoulette(tempRoulette);
    }

    @PatchMapping("/closeR/{id}")
    public Map<String, String> closeRoulette(@PathVariable("id")  final String id){
        Roulette tempRoulette = new Roulette(id,"","");

        return rouletteRepository.closeRoulette(tempRoulette);
    }
}
