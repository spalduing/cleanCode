package com.spalduing.cache.springredisbetroulet.gresource;

import com.spalduing.cache.springredisbetroulet.gclass.Gambler;
import com.spalduing.cache.springredisbetroulet.grepository.GamblerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rest/gambler")
public class GamblerResource {
    final private GamblerRepository gamblerRepository;

    public GamblerResource(GamblerRepository gamblerRepository) {
        this.gamblerRepository = gamblerRepository;
    }


    @PostMapping("/addG")
    public Gambler addG(@RequestBody Gambler gambler){
        gamblerRepository.save(gambler);
        return gamblerRepository.findById(gambler);
    }
    @PostMapping("/updateG/{id}/{name}/{rId}/{bet}/{color}")
    public Gambler updateG(@PathVariable("id") final String id, @PathVariable("name") final String name,
                           @PathVariable("bet") final String bet, @PathVariable("rId") final String rouletteId,
                           @PathVariable("color") final String color){
        gamblerRepository.update(new Gambler( id , name, rouletteId, Integer.parseInt(bet), Boolean.parseBoolean(color)));
        return gamblerRepository.findById(new Gambler(id, name,rouletteId, Integer.parseInt(bet),  Boolean.parseBoolean(color)));
    }
    @GetMapping("/findG/{id}")
    public Gambler findG(@PathVariable("id") final String id){
       return gamblerRepository.findById(new Gambler(id, "", "0", 0, false));
    }
    @GetMapping("/allG")
    public Map<String, Gambler> allG(){
        return gamblerRepository.findAll();
    }

    @DeleteMapping("/delG/{id}")
    public void delG(@PathVariable("id") final String id){
        gamblerRepository.delete(new Gambler(id, "", "0", 0, false));
    }
}
