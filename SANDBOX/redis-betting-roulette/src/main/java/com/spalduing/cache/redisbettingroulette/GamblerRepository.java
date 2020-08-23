package com.spalduing.cache.redisbettingroulette;

import com.spalduing.cache.redisbettingroulette.gambler.Gambler;

import java.util.List;
import java.util.Map;

public interface GamblerRepository {
    void save(Gambler gambler);
    Map<String, Gambler> findAll();
    Gambler findById(Gambler gambler);
    void update(Gambler gambler);
    void delete(Gambler gambler);
}
