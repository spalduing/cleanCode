package com.spalduing.cache.springredisbetroulet.gclass;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Roulette implements Serializable {
    private String id;
    private String name;
    private String betState;
    private final int upperNumberBound = 36;
    private final int upperColorBound = 1;

    public Roulette(String id, String name, String betState) {
        this.id = id;
        this.name = name;
        this.betState = betState;
    }

    public Map<String, Integer> playBet(String betColor, int betNumber){
        Random rand = new Random();
        String[] rouletteColors = {"red","black"};
        int colorPosition = rand.nextInt(upperColorBound);
        int winnerNumber = rand.nextInt(upperNumberBound);
        String winnerColor = rouletteColors[colorPosition];
        Map<String, Integer> res = new HashMap<>();
        res.put(winnerColor,winnerNumber);
        return res;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBetState() {
        return betState;
    }

    public void setBetState(String betState) {
        this.betState = betState;
    }
}
