package com.spalduing.cache.springredisbetroulet.gresource;

import com.spalduing.cache.springredisbetroulet.gclass.Roulette;
import com.spalduing.cache.springredisbetroulet.grepository.RouletteRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/roulette")
public class RouletteResource {
    final private RouletteRepository rouletteRepository;

    public RouletteResource(RouletteRepository rouletteRepository) {
        this.rouletteRepository = rouletteRepository;
    }
    @PostMapping("/addR")
    public String addR(@RequestBody Roulette roulette){
        rouletteRepository.save(roulette);
        return roulette.getId();
    }

    @Post

}
