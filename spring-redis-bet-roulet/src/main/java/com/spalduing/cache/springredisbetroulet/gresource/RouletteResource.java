package com.spalduing.cache.springredisbetroulet.gresource;

import com.spalduing.cache.springredisbetroulet.gclass.Roulette;
import com.spalduing.cache.springredisbetroulet.grepository.RouletteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rest/roulette")
public class RouletteResource {
    final private RouletteRepository rouletteRepository;

    public RouletteResource(RouletteRepository rouletteRepository) {
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

        return rouletteRepository.openRoulette(new Roulette(idR,"",""));
    }

    @PatchMapping("/closeR/{idR}")
    public Map<String, String> closeR(@PathVariable("idR")  final String idR){

        return rouletteRepository.closeRoulette(new Roulette(idR,"",""));
    }
}
