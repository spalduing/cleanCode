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
    public Map<String, Roulette> allR(){

        return rouletteRepository.findAll();
    }
    @PostMapping("/addR")
    public String addR(@RequestBody Roulette roulette){
        rouletteRepository.save(roulette);

        return roulette.getId();
    }
    @PatchMapping("/openR/{idR}")
    public String openR(@PathVariable("idR")  final String idR){
        Roulette tempRoulette = new Roulette(idR,"","");

        return rouletteRepository.openRoulette(tempRoulette);
    }

    @PatchMapping("/closeR/{idR}")
    public Map<String, String> closeR(@PathVariable("idR")  final String idR){
        Roulette tempRoulette = new Roulette(idR,"","");

        return rouletteRepository.closeRoulette(tempRoulette);
    }
}
