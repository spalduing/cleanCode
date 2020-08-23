package com.spalduing.cache.redisbettingroulette;

import com.spalduing.cache.redisbettingroulette.gambler.Gambler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rest/gambler")
public class GamblerResource {
    private GamblerRepository gamblerRepository;

    public GamblerResource(GamblerRepository gamblerRepository) {
        this.gamblerRepository = gamblerRepository;
    }


    @GetMapping("/addG/{id}/{name}")
    public Gambler addG(@PathVariable("id") final String id, @PathVariable("name") final String name){
        gamblerRepository.save(new Gambler(id, name, 0, false));
        return gamblerRepository.findById(new Gambler(id, name,0, false));
    }
    @GetMapping("/updateG/{id}/{name}")
    public Gambler updateG(@PathVariable("id") final String id, @PathVariable("name") final String name){
        gamblerRepository.update(new Gambler(id, name, 0, false));
        return gamblerRepository.findById(new Gambler(id, name,0, false));
    }
    @GetMapping("/allG")
    public Map<String, Gambler> allG(){
        return gamblerRepository.findAll();
    }
}
