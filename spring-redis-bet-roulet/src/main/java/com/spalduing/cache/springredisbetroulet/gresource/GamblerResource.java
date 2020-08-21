package com.spalduing.cache.springredisbetroulet.gresource;

import com.spalduing.cache.springredisbetroulet.gclass.Gambler;
import com.spalduing.cache.springredisbetroulet.grepository.GamblerRepository;
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
    @GetMapping("/findG/{id}")
    public Gambler findG(@PathVariable("id") final String id){
       return gamblerRepository.findById(new Gambler(id, "", 0, false));
    }
    @GetMapping("/allG")
    public Map<String, Gambler> allG(){
        return gamblerRepository.findAll();
    }

    @GetMapping("/delG/{id}")
    public void delG(@PathVariable("id") final String id){
        gamblerRepository.delete(new Gambler(id, "", 0, false));
    }
}
