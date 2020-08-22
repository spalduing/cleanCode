package com.spalduing.cache.springredisbetroulet.grepository;

import com.spalduing.cache.springredisbetroulet.gclass.Gambler;
import com.spalduing.cache.springredisbetroulet.gclass.Roulette;

import java.util.Map;

public interface RouletteRepository {
    void save(Roulette roulette);
    Roulette findById(Roulette roulette);
    Map<String, Gambler> getGamblers(Roulette roulette);
    Map<String, Roulette> findAll();
    String openRoulette(Roulette roulette);
    String closeRoulette(Roulette roulette);
    String playBet(String betColor, int betNumber, int betAmount);
}
