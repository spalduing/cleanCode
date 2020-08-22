package com.spalduing.cache.springredisbetroulet.gclass;

import java.io.Serializable;

public class Gambler implements Serializable {
    private String id;
    private String name;
    private String rouletteId;
    private int bet;
    private String color;

    public Gambler(String id, String name, String rouletteId, int bet, String color) {
        this.id = id;
        this.name = name;
        this.rouletteId = rouletteId;
        this.bet = bet;
        this.color = color;
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

    public String getRouletteId() {
        return rouletteId;
    }

    public void setRouletteId(String rouletteId) {
        this.rouletteId = rouletteId;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
