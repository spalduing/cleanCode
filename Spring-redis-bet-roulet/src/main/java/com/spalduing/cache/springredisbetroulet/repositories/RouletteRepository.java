package com.spalduing.cache.springredisbetroulet.repositories;

import com.spalduing.cache.springredisbetroulet.classes.Bet;
import com.spalduing.cache.springredisbetroulet.classes.Roulette;

import java.util.Map;

public interface RouletteRepository {
    void save(Roulette roulette);
    Roulette findById(Roulette roulette);
    Map<String, Bet> getBets(Roulette roulette);
    Map<String, Roulette> findAll();
    String openRoulette(Roulette roulette);
    Map<String, String> closeRoulette(Roulette roulette);
    String playBet(String betColor, int betNumber, double betAmount);
}
