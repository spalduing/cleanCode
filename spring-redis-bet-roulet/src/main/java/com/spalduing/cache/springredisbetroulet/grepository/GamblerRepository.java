package com.spalduing.cache.springredisbetroulet.grepository;

import com.spalduing.cache.springredisbetroulet.gclass.Gambler;

import java.util.Map;

public interface GamblerRepository {
    void save(Gambler gambler);
    Map<String, Gambler> findAll();
    Gambler findById(Gambler gambler);
    void update(Gambler gambler);
    void delete(Gambler gambler);
}
