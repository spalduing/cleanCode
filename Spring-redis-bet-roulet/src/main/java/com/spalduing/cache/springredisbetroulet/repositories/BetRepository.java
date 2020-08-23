package com.spalduing.cache.springredisbetroulet.repositories;

import com.spalduing.cache.springredisbetroulet.classes.Bet;

import java.util.Map;

public interface BetRepository {
    void save(Bet bet);
    Map<String, Bet> findAll();
    Bet findById(Bet bet);
}
